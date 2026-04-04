package com.app.guild.member;

import com.app.guild.role.data.Role;
import lombok.Data;

import java.util.List;
@Data
public class GuildMember {
    private String id;
    private String guildId;
    private List<Role> permissions;
}
