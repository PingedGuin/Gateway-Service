package com.app.guild.role.data;

import lombok.Data;

@Data
public class Role {
    private String roleId;
    private String roleName;
    private String guildId;
    private int permission;
}
