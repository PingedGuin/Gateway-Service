package com.app.message.data.dto.chat.command;

public class JoinRequest implements ChatRequest{
    private Long channelId;
    private Long userId;
    private Long guildId;
    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public Long getChannelId() {
        return channelId;
    }
    @Override
    public Long getGuildId(){
        return guildId;
    }
}
