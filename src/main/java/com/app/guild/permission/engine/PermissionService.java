package com.app.guild.permission.engine;

import com.app.channel.Entity.ChannelEntity;
import com.app.channel.data.ChannelDto;
import com.app.channel.service.ChannelService;
import com.app.guild.permission.data.dto.ChannelPermsDto;
import com.app.member.dto.MemberDto;
import com.app.member.entity.MemberEntity;
import com.app.member.service.MemberService;
import com.app.role.dto.RoleDto;
import com.app.role.entity.RoleOverride;
import com.app.user.service.UserService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    private final ChannelService channelService;
    private final MemberService memberService;

    private final Cache<String, Long> cache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .build();

    public PermissionService(ChannelService channelService, MemberService memberService) {
        this.channelService = channelService;
        this.memberService = memberService;
    }

    public Long getPermission(MemberDto member, ChannelDto channel) {
        String key = String.format("perm:%s:%s:%s",
                member.getGuildId(),
                channel.getChannelId(),
                member.getId());

        Long cachedPerm = cache.getIfPresent(key);
        if (cachedPerm != null) return cachedPerm;

        var channelContext = channelService.getChannelPermissions(member.getGuildId(), channel.getChannelId(), member.getId());
        var memberDto = memberService.getUserPermissions(member.getId(), member.getGuildId());
        Long perm = calculate(memberDto, channelContext);
        cache.put(key, perm);
        return perm;
    }

    private Long calculate(MemberDto member, ChannelPermsDto channel) {
        Long perms = 0L;

        for (RoleDto role : member.getRoles()) {
            perms |= role.getPermission();
        }

        for (RoleOverride override : channel.getRoleOverrideMap().values()) {
            if (override != null && override.getRole() != null) {
                if (memberService.hasRole(override.getRole().getId())) {
                    perms &= ~override.getDeny();
                    perms |= override.getAllow();
                }
            }
        }

        if (channel.getMemberOverride() != null) {
            perms &= ~channel.getMemberOverride().getDeniedPermissions();
            perms |= channel.getMemberOverride().getAllowedPermissions();
        }

        return perms;
    }
}