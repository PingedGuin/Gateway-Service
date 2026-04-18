package com.app.message.data.dto;

import com.app.policy.PolicyContext;
import lombok.Getter;

@Getter
public class ChatMessageDto {
    private String sender;
    private String channelId;
    private final String guildId;
    private String timestamp;
    private String type;
    private final String content;

    public ChatMessageDto(PolicyContext context) {
        this.channelId = context.getChannelId();
        this.content = context.getContent();
        this.channelId = context.getChannelId();
        this.guildId = context.getGuildId();
    }
}
