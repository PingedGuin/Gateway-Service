package com.app.message.config;

import com.app.message.data.dto.ChatMessageDto;
import com.app.message.service.WebSocketService;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class RedisSubscriber {

    private final WebSocketService webSocketService;
    private final ObjectMapper objectMapper;

    public RedisSubscriber(WebSocketService webSocketService, ObjectMapper objectMapper) {
        this.webSocketService = webSocketService;
        this.objectMapper = objectMapper;
    }
    public void onMessage(String message) {
        try {
            ChatMessageDto dto = objectMapper.readValue(message, ChatMessageDto.class);
            webSocketService.sendMessage(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}