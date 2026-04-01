package com.app.service.database.repository;

import com.app.service.database.entitys.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
    Optional<UserInfoEntity> findByUsername(String username, String password);
    boolean existsByGmailAndUsername(String gmail, String username);}
