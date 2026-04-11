package com.app.member.service;

import com.app.guild.service.GuildService;
import com.app.member.dto.MemberDto;
import com.app.member.entity.MemberEntity;
import com.app.member.repository.MemberRepository;
import com.app.role.dto.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDto getUserPermissions(Long id, Long guildId) {
        return null;
    }

    public MemberDto getMemberContext(Long userId, Long guildId) {

        MemberEntity entity = memberRepository
                .findByUserIdAndGuildId(userId, guildId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        List<RoleDto> roles = entity.getRoles()
                .stream()
                .map(role -> new RoleDto(
                        role.getId(),
                        role.getName(),
                        role.getGuild().getGuildId(),
                        role.getPermission()
                ))
                .toList();

        Set<Long> roleIds = roles.stream()
                .map(RoleDto::getRoleId)
                .collect(Collectors.toSet());


        MemberDto dto = new MemberDto();
        dto.setUserId(entity.getUserInfo().getId());
        dto.setUsername(entity.getUsername());
        dto.setRoles(roles);
        dto.setGuildId(guildId);
        dto.setRoleIds(roleIds);

        return dto;
    }

}
