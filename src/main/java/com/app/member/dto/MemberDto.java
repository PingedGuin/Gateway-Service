package com.app.member.dto;

import com.app.role.dto.RoleDto;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class MemberDto {
    private Long userId;
    private String username;
    private Long guildId;
    private List<RoleDto> Roles;
    private Set<Long> roleIds;
}
