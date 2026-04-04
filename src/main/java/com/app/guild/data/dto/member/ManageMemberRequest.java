package com.app.guild.data.dto.member;

import lombok.Data;

@Data
public class ManageMemberRequest {
    private String guildId;
    private String userId;
    private String roleId;
    private String permission;
    private String channelId;
}
