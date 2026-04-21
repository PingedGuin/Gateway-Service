package com.app.message.service;

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
    Map<String, Set<String>> channelUsers;
    private final ObjectMapper objectMapper = new ObjectMapper();

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

        for (String userId : users) {
            Session sessionWrapper = sessions.get(userId);

            if (sessionWrapper != null) {
                try {
                    String json = objectMapper.writeValueAsString(messageDto);
                    sessionWrapper.getSession().sendMessage(new TextMessage(json));
                } catch (Exception e) {
                    // handle
                }
            }
        }
    }
}
