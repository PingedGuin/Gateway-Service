package com.app.guild.repository;

import com.app.guild.data.Entity.GuildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuildRepository extends JpaRepository <GuildEntity, Integer> {
        Optional<GuildEntity> findById(Long id);
}
