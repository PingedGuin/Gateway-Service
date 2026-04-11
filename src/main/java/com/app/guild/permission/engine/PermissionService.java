package com.app.guild.permission.engine;

import com.app.channel.Entity.ChannelEntity;
import com.app.channel.data.ChannelDto;
import com.app.channel.service.ChannelService;
import com.app.guild.permission.data.dto.ChannelPermsDto;
import com.app.member.dto.MemberDto;
import com.app.member.entity.MemberEntity;
import com.app.member.service.MemberService;
import com.app.user.service.UserService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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

        // Calculate permission call the repository here and get member - channel override ig
        var channelPermissions = channelService.getChannelPermissions(member.getGuildId(),channel.getChannelId(),member.getId());
        var memberDto = memberService.getUserPermissions(member.getId(),member.getGuildId());
        Long perm = calculate(memberDto,channelPermissions);
        cache.put(key, perm);
        return perm;
    }
    private Long calculate(MemberDto member, ChannelPermsDto channel) {
        Long permission = 0L;


        return 1L;
    }
}