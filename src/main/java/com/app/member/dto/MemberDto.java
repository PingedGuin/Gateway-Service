package com.app.member.dto;

import lombok.Data;


@Data
public class MemberDto {
    private Long userId;
    private String username;
    private Long guildId;
}
