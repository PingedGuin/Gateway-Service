package com.app.guild.data.dto.member;

import com.app.guild.data.dto.GuildRequest;
import lombok.Data;

@Data
public class ManageMemberRequest implements GuildRequest {
    private String guildId;
    private String userId;
    private String roleId;
    private String permission;
    private String channelId;
}
