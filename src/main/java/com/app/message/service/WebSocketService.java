package com.app.message.service;

import com.app.message.config.jackson.JacksonConfig;
import com.app.message.data.dto.ChatMessageDto;
import com.app.websocket.Session;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;

@Service
@Getter
public class WebSocketService {
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> channelUsers = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;
    WebSocketService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void addSession(String userId, Session session) {
        sessions.put(userId, session);
    }

    public void removeSession(String userId) {
        sessions.remove(userId);
    }

    public void sendMessage(ChatMessageDto messageDto) {
        String channelId = messageDto.getChannelId();
        Set<String> users = channelUsers.get(channelId);

        if (users == null) return;

        try {
            String json = objectMapper.writeValueAsString(messageDto);

            for (String userId : users) {
                Session sessionWrapper = sessions.get(userId);

                if (sessionWrapper != null) {
                    sessionWrapper.getSession().sendMessage(new TextMessage(json));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void joinChannel(String channelId, String userId) {
        channelUsers
                .computeIfAbsent(channelId, k -> ConcurrentHashMap.newKeySet())
                .add(userId);
    }

    public void leaveChannel(String channelId, String userId) {
        Set<String> users = channelUsers.get(channelId);
        if (users != null) {
            users.remove(userId);
        }
    }
}
