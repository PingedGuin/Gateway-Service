package com.app.service.security.auth;

import com.app.service.database.entitys.UserSessionEntity;
import com.app.service.database.repository.SessionRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @CachePut(value = "userSession", key = "#userSessionEntity.userId")
    public UserSessionEntity getOrCreateSession(UserSessionEntity userSessionEntity) {
        return sessionRepository.findByUserId(userSessionEntity.getUserId())
                .orElseGet(() -> sessionRepository.save(userSessionEntity));
    }
}