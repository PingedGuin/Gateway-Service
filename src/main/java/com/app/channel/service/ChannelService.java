package com.app.channel.service;

import com.app.channel.Entity.ChannelEntity;
import com.app.channel.repository.ChannelRepository;
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
    private final ChannelRepository ChannelRepository;

    public ChannelService(ChannelRepository repository) {
        this.ChannelRepository = repository;
    }

    private final Cache<String, ChannelEntity> cacheEntity = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    private final Cache<String,MemberOverride> cacheOverrideMemberPerms = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    private final Cache<String,Long> cacheOverrideRolesPerms = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public Long getChannelPermissions(String guildId, String channelId,String memberId) {
        String key = getChannelPermissionKey(guildId,channelId,memberId);

        Long perms = cachePerms.getIfPresent(key);

        if(perms != null) return perms;

        ChannelEntity channel = cacheEntity.getIfPresent(channelId);

        if (channel == null) {
            channel = ChannelRepository.findById(Integer.parseInt(channelId)).orElseThrow();
            cacheEntity.put(channelId, channel);
        }

        Map<Long, MemberOverride> memberOverrideMap = channel.getMemberOverrides().stream()
                .collect(Collectors.toMap(mo -> mo.getMember().getId(), mo -> mo));

        Map<String, RoleOverride> roleOverrideMap = channel.getRoleOverrides().stream()
                .collect(Collectors.toMap(ro -> ro.getRole().getId().toString(), ro -> ro));

        cacheOverrideMemberPerms.put(memberId,memberOverrideMap);


    }

    private String getChannelPermissionKey(String guildId, String channelId, String memberId) {
        return String.format("perm:%s:%s:%s",guildId,channelId,memberId);
    }
}
