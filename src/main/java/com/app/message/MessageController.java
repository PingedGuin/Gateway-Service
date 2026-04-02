package com.app.message;

import com.app.register.dtos.socket.SocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage")
    public SocketMessage send(@Payload SocketMessage msg) {
        if (msg.getContent() == null || msg.getContent().isEmpty()) {
            throw new RuntimeException("Invalid message");
        }

        log.info("Message received: {}", msg.getContent());
        messagingTemplate.convertAndSend("/topic/" + msg.getRoom(), msg);
        return msg;
    }
}
