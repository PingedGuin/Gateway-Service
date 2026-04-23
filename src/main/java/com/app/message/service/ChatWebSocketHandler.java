package com.app.message.service;

import com.app.message.data.dto.ChatMessageDto;
import com.app.websocket.Session;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final WebSocketService webSocketService;

    public ChatWebSocketHandler(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        String userId = getUserId(session);

        webSocketService.addSession(userId, new Session(session));
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();

        ChatMessageDto dto = new ObjectMapper().readValue(payload, ChatMessageDto.class);

        webSocketService.sendMessage(dto);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        String userId = getUserId(session);

        webSocketService.removeSession(userId);
    }

    private String getUserId(WebSocketSession session) {
        return session.getId();
    }
}