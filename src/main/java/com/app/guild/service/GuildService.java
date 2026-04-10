package com.app.guild.service;

import com.app.guild.data.Entity.GuildEntity;
import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.mapping.GuildMapper;
import com.app.guild.repository.GuildRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class GuildService {
    private final GuildRepository guildRepository;
    private final GuildMapper mapper;

    private final Cache<Long, GuildInfoDto> guildCache = Caffeine.newBuilder()
            .maximumSize(200_000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();


    public GuildService(GuildRepository guildRepository, GuildMapper mapper) {
        this.guildRepository = guildRepository;
        this.mapper = mapper;
    }

    public GuildInfoDto getGuild(Long guildId) {
        var dto = guildCache.getIfPresent(guildId);

        if (dto != null) return dto;

        GuildEntity guildEntity = guildRepository.findById(guildId).orElse(null);

        if (guildEntity == null) throw new RuntimeException("Guild not found");
        GuildInfoDto mapped = mapper.toDto(guildEntity);

        guildCache.put(guildId, mapped);
        return mapped;
    }
}
