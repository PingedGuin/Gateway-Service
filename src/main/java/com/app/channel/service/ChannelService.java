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
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository repository) {
        this.channelRepository = repository;
    }

    private final Cache<String, ChannelEntity> cacheEntity = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    private final Cache<String, Map<Long, MemberOverride>> cacheOverrideMemberPerms = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    private final Cache<String, Map<Long, RoleOverride>> cacheOverrideRolesPerms = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();


    public ChannelPermsDto getChannelPermissions(String guildId, String channelId, String memberId) {
        ChannelEntity channel = cacheEntity.getIfPresent(channelId);

        if (channel == null) {
            channel = channelRepository.findById(channelId)
                    .orElseThrow(() -> new RuntimeException("Channel not found"));
            cacheEntity.put(channelId, channel);
        }

        Map<Long, MemberOverride> memberOverrideMap = cacheOverrideMemberPerms.getIfPresent(channelId);
        Map<Long, RoleOverride> roleOverrideMap = cacheOverrideRolesPerms.getIfPresent(channelId);

        if (memberOverrideMap == null) {
            memberOverrideMap = channel.getMemberOverrides().stream()
                    .collect(Collectors.toMap(mo -> mo.getMember().getId(), mo -> mo));
            cacheOverrideMemberPerms.put(channelId, memberOverrideMap);
        }
        if (roleOverrideMap == null) {
            roleOverrideMap = channel.getRoleOverrides().stream()
                    .collect(Collectors.toMap(ro -> ro.getRole().getId(), ro -> ro));

            cacheOverrideRolesPerms.put(channelId, roleOverrideMap);
        }
        return new ChannelPermsDto(channel.getGuildId(), channel.getId(), roleOverrideMap, memberOverrideMap);

    }

}
