package com.app.role.dto;

import lombok.Data;

@Data
public class RoleDto {
    private Long roleId;
    private String roleName;
    private Long guildId;
    private Long permission;

    public RoleDto(Long roleId, String roleName, Long guildId, Long permission) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.guildId = guildId;
        this.permission = permission;
    }
}
