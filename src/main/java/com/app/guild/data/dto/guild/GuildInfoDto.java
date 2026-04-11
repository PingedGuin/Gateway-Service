package com.app.guild.data.dto.guild;

import com.app.guild.data.Entity.GuildEntity;
import lombok.Data;

@Data
public class GuildInfoDto {
    private Long guildId;
    private String guildName;
    private String guildIcon;
}
