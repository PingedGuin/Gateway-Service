package com.app.message.data.dto.chat.command;

public interface ChatRequest {
    Long getUserId();
    Long getChannelId();
    Long getGuildId();
}
