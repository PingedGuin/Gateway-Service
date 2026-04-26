package com.app.message.service;

import com.app.message.data.dto.ChatMessageDto;
import com.app.message.data.dto.LoadMessagesRequest;
import com.app.message.data.entity.MessageEntity;
import com.app.message.repository.MessageRepository;
import com.app.policy.PolicyEngine;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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

    public void handleSendMsgReq(ChatMessageDto context) {
        policyEngine.check(context);
        MessageEntity messageEntity = toMessageEntity(context);
        var savedMessage = messageRepository.save(messageEntity);
        ChatMessageDto dto = toDto(savedMessage);

        webSocketService.sendMessage(dto);
    }

    public List<ChatMessageDto> getGeneralMessages(LoadMessagesRequest loadMsgequest) {
        Pageable page = PageRequest.of(loadMsgequest.getLimit(), loadMsgequest.getOffset());
        messageRepository.getGeneralMessages(loadMsgequest.getChannelId(), page);
        return null; //todo : let method return messages from db-cache with pagination :D
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
