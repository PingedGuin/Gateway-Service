package com.app.message.service;

import com.app.message.data.dto.ChatMessageDto;
import com.app.message.data.dto.LoadMessagesRequest;
import com.app.message.data.entity.MessageEntity;
import com.app.message.repository.MessageRepository;
import com.app.policy.PolicyEngine;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MessageService {
    private final PolicyEngine policyEngine;
    private final WebSocketService webSocketService;
    private final MessageRepository messageRepository;
    private final Cache<String, List<ChatMessageDto>> msgCache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public MessageService(PolicyEngine policyEngine, WebSocketService webSocketService, MessageRepository messageRepository) {
        this.policyEngine = policyEngine;
        this.webSocketService = webSocketService;
        this.messageRepository = messageRepository;
    }

    public void handleSendMsgReq(ChatMessageDto context) {
        policyEngine.check(context);
        MessageEntity messageEntity = toMessageEntity(context);
        var savedMessage = messageRepository.save(messageEntity);
        ChatMessageDto dto = toDto(savedMessage);

        webSocketService.sendMessage(dto);
    }

    public List<ChatMessageDto> getGeneralMessages(LoadMessagesRequest request) {
        String key = request.getChannelId() + ":" +
                request.getPageSize() + ":" +
                request.getPageNumber();

        List<ChatMessageDto> cached =
                msgCache.getIfPresent(key);

        if (cached != null) return cached;

        Pageable page = PageRequest.of(
                request.getPageNumber(),
                request.getPageSize()
        );

        List<MessageEntity> messages =
                messageRepository.getGeneralMessages(
                        request.getChannelId(),
                        page
                );

        List<ChatMessageDto> result =
                messages.stream()
                        .map(this::toDto)
                        .toList();

        msgCache.put(key, result);

        return result;
    }

    private MessageEntity toMessageEntity(ChatMessageDto context) {
        MessageEntity entity = new MessageEntity();

        entity.setContent(context.getContent());
        entity.setGuildId(context.getGuildId());
        entity.setChannelId(context.getChannelId());
        entity.setUserId(context.getSenderId());
        entity.setCreatedAt(Instant.now());

        return entity;
    }

    private ChatMessageDto toDto(MessageEntity entity) {
        ChatMessageDto dto = new ChatMessageDto();

        dto.setContent(entity.getContent());
        dto.setGuildId(entity.getGuildId());
        dto.setChannelId(entity.getChannelId());
        dto.setSenderId(entity.getUserId());
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
