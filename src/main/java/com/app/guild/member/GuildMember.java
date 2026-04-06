package com.app.guild.member;

import com.app.role.dto.RoleDto;
import lombok.Data;

import java.util.List;
@Data
public class GuildMember {
    private String id;
    private String guildId;
    private List<RoleDto> permissions;
}
