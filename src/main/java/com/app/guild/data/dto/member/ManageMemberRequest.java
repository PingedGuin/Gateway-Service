package com.app.guild.data.dto.member;

import com.app.guild.data.dto.GuildOperationRequest;
import lombok.Data;

@Data
public class ManageMemberRequest implements GuildOperationRequest {
    private String guildId;
    private String userId;
    private String roleId;
    private String channelId;
    private String permission;
}
