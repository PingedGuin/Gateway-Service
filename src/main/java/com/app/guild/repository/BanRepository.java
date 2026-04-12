package com.app.guild.repository;

import com.app.guild.data.Entity.command.BanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BanRepository extends JpaRepository <BanEntity, Long>{
    Optional<BanEntity> findByUserId(String userId);
}
