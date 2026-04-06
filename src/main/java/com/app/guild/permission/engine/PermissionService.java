package com.app.guild.permission.engine;

import com.app.channel.data.Channel;
import com.app.guild.member.dto.MemberDto;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class PermissionService {

    private final Cache<String, Long> cache = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public Long getPermission(MemberDto member, String memberId, Channel channel) {
        String key = String.format("perm:%s:%s:%s",
                member.getGuildId(),
                channel.getChannelId(),
                member.getId());

        Long cachedPerm = cache.getIfPresent(key);
        if (cachedPerm != null) return cachedPerm;

        Long perm = calculate(member,channel);
        cache.put(key, perm);
        return perm;
    }
    private Long calculate(MemberDto member, Channel channel) {
        Long permission = 0L;


        return 1L;
    }
}