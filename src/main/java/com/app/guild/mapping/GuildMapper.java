package com.app.guild.mapping;

import com.app.guild.data.Entity.GuildEntity;
import com.app.guild.data.dto.guild.GuildInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GuildMapper {
    @Mapping(target = "members", ignore = true)
    GuildInfoDto toDto(GuildEntity entity);

    @Mapping(target = "id", ignore = true)
    GuildEntity toEntity(GuildInfoDto dto);

}
