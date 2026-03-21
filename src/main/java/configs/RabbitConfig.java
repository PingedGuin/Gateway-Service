package configs;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "message.exchange";
    public static final String DLX = "dlx.exchange";

    public static final String QUEUE = "save-queue";
    public static final String DLQ = "save-queue-dlq";

    public static final String RETRY_QUEUE = "save-queue-retry";
    public static final String RETRY_EXCHANGE = "retry.exchange";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DLX);
    }

    @Bean
    public DirectExchange retryExchange() {
        return new DirectExchange(RETRY_EXCHANGE);
    }

    @Bean
    public Queue saveQueue() {
        return QueueBuilder.durable(QUEUE)
                .deadLetterExchange(DLX)
                .deadLetterRoutingKey("dlq.key")
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(DLQ).build();
    }

    @Bean
    public Queue retryQueue() {
        return QueueBuilder.durable(RETRY_QUEUE)
                .deadLetterExchange(EXCHANGE)
                .deadLetterRoutingKey("message.created")
                .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(saveQueue())
                .to(exchange())
                .with("message.created");
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder
                .bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with("dlq.key");
    }

    @Bean
    public Binding retryBinding() {
        return BindingBuilder
                .bind(retryQueue())
                .to(retryExchange())
                .with("retry.key");
    }
}