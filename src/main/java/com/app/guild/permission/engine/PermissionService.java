package com.app.guild.permission.engine;

import com.app.channel.Entity.ChannelEntity;
import com.app.channel.data.ChannelDto;
import com.app.member.dto.MemberDto;
import com.app.member.entity.MemberEntity;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class PermissionService {

    private final Cache<String, Long> cache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public Long getPermission(MemberDto member, String memberId, ChannelDto channelDto) {
        String key = String.format("perm:%s:%s:%s",
                member.getGuildId(),
                channelDto.getChannelId(),
                member.getId());

        Long cachedPerm = cache.getIfPresent(key);
        if (cachedPerm != null) return cachedPerm;

        // Calculate permission call the repository here and get member - channel override ig
        Long perm = calculate();
        cache.put(key, perm);
        return perm;
    }
    private Long calculate(MemberEntity member, ChannelEntity channel) {
        Long permission = 0L;


        return 1L;
    }
}