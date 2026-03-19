package webSocket;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

public class MessageHandler {

@MessageMapping("/sendMessage")
@SendTo("/topic/{room}")
public Message send(@Payload Message msg) {
    System.out.println("message received: " + msg.getPayload());
    return msg;
}
}
