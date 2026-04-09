package com.app.channel.repository;

import com.app.channel.Entity.ChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository <ChannelEntity, Integer>{
    Optional<ChannelEntity> findById(String id);
}
