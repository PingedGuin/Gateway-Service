package com.app.message.data.dto;

import com.app.policy.Action;
import lombok.Data;
import java.time.Instant;

@Data
public class ChatMessageDto {
    private Long messageId;
    private Long userId;
    private String sender;
    private String channelId;
    private String guildId;
    private Instant CreatedAt;
    private String type;
    private String content;
    private String permission;
    private Action action;

    public ChatMessageDto() {

    }
}
