package com.app.message.controller;

import com.app.message.data.dto.ChatMessageDto;
import com.app.policy.PolicyEngine;
import com.app.register.dtos.socket.SocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class MessageController {
    private final PolicyEngine policyEngine;
    public MessageController(PolicyEngine policyEngine) {
        this.policyEngine = policyEngine;
    }

    @MessageMapping("/sendMessage")
    public SocketMessage send(@Payload ChatMessageDto chatMessageDto) {
        if (msg.getContent() == null || msg.getContent().isEmpty()) {
            throw new RuntimeException("Invalid message");
        }policyEngine.check(chatMessageDto);

        log.info("Message received: {}", msg.getContent());
        return msg;
    }
}  //todo change this
