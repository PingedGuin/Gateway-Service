package com.app.member.dto;

import com.app.role.dto.RoleDto;
import lombok.Data;

import java.util.List;

@Data
public class MemberDto {
    private String id;
    private String username;
    private String guildId;
    private List<RoleDto> permissions;
}
