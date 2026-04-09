package com.app.guild.data.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "guilds")
@Data
public class GuildEntity {
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private Long guildId;

    @Column(nullable = false)
    private String guildName;

    @Column(nullable = false)
    private String guildIcon;

}
