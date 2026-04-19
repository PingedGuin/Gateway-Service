package com.app.message.service;

import com.app.message.data.dto.ChatMessageDto;
import com.app.message.data.entity.MessageEntity;
import com.app.message.repository.MessageRepository;
import com.app.policy.PolicyContext;
import com.app.policy.PolicyEngine;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class MessageService {
    private final PolicyEngine policyEngine;
    private final WebSocketService webSocketService;
    private final MessageRepository messageRepository;
    public MessageService(PolicyEngine policyEngine, WebSocketService webSocketService, MessageRepository messageRepository) {
        this.policyEngine = policyEngine;
        this.webSocketService = webSocketService;
        this.messageRepository = messageRepository;
    }

    public void handleSendMsgReq(PolicyContext context) {
        // policyEngine.check(context);

        MessageEntity messageEntity = toMessageEntity(context);
        messageRepository.save(messageEntity);
        ChatMessageDto dto = toDto(messageEntity);

        webSocketService.sendMessage(dto);
    }
    private MessageEntity toMessageEntity(PolicyContext context) {
        MessageEntity entity = new MessageEntity();

        entity.setContent(context.getContent());
        entity.setGuildId(context.getGuildId());
        entity.setChannelId(context.getChannelId());
        entity.setUserId(context.getUserId());
        entity.setCreatedAt(Instant.now());

        return entity;
    }

    private ChatMessageDto toDto(MessageEntity entity) {
        ChatMessageDto dto = new ChatMessageDto();

        dto.setContent(entity.getContent());
        dto.setGuildId(entity.getGuildId());
        dto.setChannelId(entity.getChannelId());
        dto.setUserId(entity.getUserId());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }
// 1. check if user banned
// 2. check membership (user in guild)
// 3. check channel access
// 4. check permissions (SEND_MESSAGE)
// 5. create message object
// 6. save message
// 7. publish event (WebSocket)

}
