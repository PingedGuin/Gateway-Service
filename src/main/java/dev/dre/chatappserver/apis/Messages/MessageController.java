package dev.dre.chatappserver.apis.Messages;

import dev.dre.chatappserver.dtos.socket.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
@Controller
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public MessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage")
    public SocketMessage send(@Payload SocketMessage msg) {
        if(msg.getContent() == null || msg.getContent().isEmpty()) {
            throw new RuntimeException("Invalid message");
        }

        System.out.println("Message received: " + msg.getContent());
        messagingTemplate.convertAndSend("/topic/" + msg.getRoom(), msg);
        return msg;
    }
}
