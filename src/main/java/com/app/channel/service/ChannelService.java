package com.app.channel.service;

import com.app.channel.Entity.ChannelEntity;
import com.app.channel.repository.ChannelRepository;
import com.app.guild.permission.data.dto.ChannelPermsDto;
import com.app.member.entity.MemberOverride;
import com.app.role.entity.RoleOverride;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository repository) {
        this.channelRepository = repository;
    }

    private final Cache<String, ChannelEntity> cacheEntity = Caffeine.newBuilder()
            .maximumSize(50000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    private final Cache<String, Optional<MemberOverride>> memberOverridePermsCache = Caffeine.newBuilder()
            .maximumSize(200_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    private final Cache<String, Map<Long, RoleOverride>> cacheOverrideRolesPerms = Caffeine.newBuilder()
            .maximumSize(50000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();


    public ChannelPermsDto getChannelPermissions(String guildId, String channelId, Long memberId) {
        String key = String.format("channel:%s:%s", guildId, channelId);

        ChannelEntity channel = cacheEntity.get(channelId, id -> channelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Channel not found"))
        );

        String memberPermskey = "member:" + channelId + ":" + memberId;
        Optional<MemberOverride> memberOverride = memberOverridePermsCache.get(memberPermskey, k ->
                channelRepository.findByChannelIdAndMemberId(channel.getId(), memberId)
        );
        Map<Long, RoleOverride> roleOverrides = cacheOverrideRolesPerms.get(channelId, k ->
                channel.getRoleOverrides().stream()
                        .collect(Collectors.toMap(
                                ro -> ro.getRole().getId(),
                                ro -> ro
                        ))
        );

        return new ChannelPermsDto(channel.getGuildId(), channel.getId(), roleOverrides, memberOverride.orElse(null));
    }

    public void evictChannel(String key) {
        cacheEntity.invalidate(key);
        memberOverridePermsCache.invalidate(key);
        cacheOverrideRolesPerms.invalidate(key);
    }

}
