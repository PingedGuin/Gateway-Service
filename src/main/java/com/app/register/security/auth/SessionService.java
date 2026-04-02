package com.app.register.security.auth;

import com.app.register.database.entitys.UserSessionEntity;
import com.app.register.database.repository.SessionRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "userSession", key = "#userId")
    public UserSessionEntity getSession(String userId) {
        return sessionRepository.findByUserId(userId).orElse(null);
    }
    @CacheEvict(value = "userSession", key = "#userId")
    public boolean deleteSession(String userId) {
        return sessionRepository.deleteByUserId(userId);
    }
}