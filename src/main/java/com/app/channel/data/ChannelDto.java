package com.app.channel.data;

import lombok.Data;

@Data
public class ChannelDto {
    private String channelId;
    private String channelName;
    private String guildId;
    private String channelDescription;
    private String region;
    private Long permission;
}
