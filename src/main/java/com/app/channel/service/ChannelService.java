package com.app.channel.service;

import com.app.channel.Entity.ChannelEntity;
import com.app.channel.repository.ChannelRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

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

    public Long getChannelPermission(String guildId, String channelId) {
        String key = String.format("perm:%s:%s",guildId,channelId);

        Long perms = cachePerms.getIfPresent(key);

        if(perms != null) return perms;

        perms = cacheEntity.getIfPresent(key).getRoleOverrides();
    }
}
