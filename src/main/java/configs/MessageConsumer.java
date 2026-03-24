package configs;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class MessageConsumer {

    private final RabbitTemplate rabbitTemplate;

    public MessageConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE, ackMode = "MANUAL")
    public void consume(Message message, Channel channel) throws IOException {

        MessageProperties props = message.getMessageProperties();
        Integer retryCount = (Integer) props.getHeaders().getOrDefault("x-retry-count", 0);

        String body = new String(message.getBody());

        try {
            log.info("Processing message: {}", body);

            processMessage(body);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("Message processed successfully");

        } catch (IllegalStateException e) {
            handleRetry(message, retryCount, e);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            sendToDLQ(message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            log.error("Fatal error → sent to DLQ: {}", e.getMessage());
        }
    }

    private void processMessage(String body) {

        if (body.contains("fail")) throw new IllegalStateException("Business failure");

        if (body.contains("error")) throw new RuntimeException("Unexpected error");

        log.info("Processed: {}", body);
    }

    private void handleRetry(Message message, int retryCount, Exception e) {

        retryCount++;

        log.warn("Retry #{} due to error: {}", retryCount, e.getMessage());

        if (retryCount <= 3) {

            int delay = (int) Math.pow(2, retryCount) * 10000;

            MessageProperties newProps = new MessageProperties();
            newProps.getHeaders().putAll(message.getMessageProperties().getHeaders());
            newProps.getHeaders().put("x-retry-count", retryCount);
            newProps.setExpiration(String.valueOf(delay));
            Message retryMessage = new Message(message.getBody(), newProps);

            rabbitTemplate.send(
                    RabbitConfig.RETRY_EXCHANGE,
                    "retry.key",
                    retryMessage
            );

            log.info("Message sent for retry after {} ms", delay);

        } else {
            sendToDLQ(message);
            log.error("Message sent to DLQ after max retries");
        }
    }

    private void sendToDLQ(Message message) {
        rabbitTemplate.send(
                RabbitConfig.DLX,
                "dlq.key",
                message
        );
    }
}