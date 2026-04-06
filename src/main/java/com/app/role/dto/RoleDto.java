package com.app.role.dto;

import lombok.Data;

@Data
public class RoleDto {
    private String roleId;
    private String roleName;
    private String guildId;
    private Long permission;
}
