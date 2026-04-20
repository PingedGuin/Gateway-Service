package com.app.message.service;

import com.app.message.data.dto.ChatMessageDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(ChatMessageDto messageDto) {
        String destination =
                "/topic/guild/" + messageDto.getGuildId()
                        + "/channel/" + messageDto.getChannelId();

        messagingTemplate.convertAndSend(destination, messageDto);
    }
    public void subscribe(String channelId) {
        String destination = "/topic/guild/" + channelId;

        
    }
}
