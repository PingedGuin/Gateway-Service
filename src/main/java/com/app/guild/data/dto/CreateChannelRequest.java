package com.app.guild.data.dto;

import lombok.Data;

@Data
public class CreateChannelRequest {
    private String channelName;
    private String channelType;
    private String guildId;
    private String channelDescription;
    private String region;
    private String permission;
}
