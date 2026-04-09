package com.app.guild.permission.data.dto;

import com.app.member.entity.MemberOverride;
import com.app.role.entity.RoleOverride;
import lombok.Data;

import java.util.Map;
@Data
public class ChannelPermsDto {
    private String channelId;
    private String guildId;
    private Map<Long,MemberOverride> memberOverrideMap;
    private Map<Long, RoleOverride> roleOverrideMap;

    public ChannelPermsDto(String guildId, Long id, Map<Long, RoleOverride> roleOverrideMap, Map<Long, MemberOverride> memberOverrideMap) {
        this.guildId = guildId;
        this.channelId = id.toString();
        this.roleOverrideMap = roleOverrideMap;
        this.memberOverrideMap = memberOverrideMap;
    }
}
