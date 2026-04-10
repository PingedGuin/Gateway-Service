package com.app.guild.mapping;

import com.app.guild.data.Entity.GuildEntity;
import com.app.guild.data.dto.guild.GuildInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GuildMapper {
    @Mapping(target = "members", ignore = true)
    GuildInfoDto toDto(GuildEntity entity);
    void updateEntityFromDto(GuildInfoDto dto, @MappingTarget GuildEntity entity);

}
