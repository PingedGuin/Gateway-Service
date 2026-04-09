package com.app.guild.service;

import com.app.guild.data.Entity.GuildEntity;
import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.repository.GuildRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class GuildService {
    private final GuildRepository guildRepository;

    private final Cache<Long, GuildEntity> guildCache = Caffeine.newBuilder()
            .maximumSize(200_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();


    public GuildService(GuildRepository guildRepository) {
        this.guildRepository = guildRepository;
    }
    public GuildInfoDto getGuild(Long guildId) {
        return guildCache.get(guildId, _ -> new GuildInfoDto(guildRepository.findById(guildId).orElseThrow(null)));
    }
}
