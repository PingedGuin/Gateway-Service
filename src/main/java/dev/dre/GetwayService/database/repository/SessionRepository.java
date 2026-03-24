package dev.dre.GetwayService.database.repository;

import dev.dre.GetwayService.database.entitys.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<UserSessionEntity, Long> {
    Optional<UserSessionEntity> findBySessionId(String sessionId);

    Optional<UserSessionEntity> findByUserId(String userId);
}
