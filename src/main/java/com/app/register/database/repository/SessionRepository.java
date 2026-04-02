package com.app.register.database.repository;

import com.app.register.database.entitys.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<UserSessionEntity, Long> {
    Optional<UserSessionEntity> findBySessionId(String sessionId);

    Optional<UserSessionEntity> findByUserId(String userId);

    boolean deleteByUserId(String userId);
}
