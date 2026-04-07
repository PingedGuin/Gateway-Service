package com.app.channel.service;

import com.app.channel.Entity.ChannelEntity;
import com.app.channel.repository.ChannelRepository;
import com.app.member.entity.MemberOverride;
import com.app.role.entity.RoleOverride;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Service
public class ChannelService {
    private final ChannelRepository repository;

    public ChannelService(ChannelRepository repository) {
        this.repository = repository;
    }

    private final Cache<String, ChannelEntity> cacheEntity = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    private final Cache<String,Long> cachePerms = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public Long getChannelPermission(String guildId, String channelId,String memberId) {
        String key = String.format("perm:%s:%s:%s",guildId,channelId,memberId);

        Long perms = cachePerms.getIfPresent(key);

        if(perms != null) return perms;

        Map<String, RoleOverride> roleOverrideMap;
        Map<String, MemberOverride> memberOverrideMap;

        ChannelEntity channel = cacheEntity.getIfPresent(channelId);
        if (channel == null) {
            channel = repository.findById(Integer.parseInt(channelId)).orElseThrow();
            cacheEntity.put(channelId, channel);
        }

        List<RoleOverride> roleOverrides = channel.getRoleOverrides();


    }
}
