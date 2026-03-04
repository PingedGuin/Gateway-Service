package dev.dre.chatappserver.security.auth.service;

import dev.dre.chatappserver.database.entitys.UserSessionEntity;
import org.springframework.cache.annotation.Cacheable;

public class SessionService {
        @Cacheable("userSession")
    public void getOrCreate(UserSessionEntity userSessionEntity) {
        // todo make data getter that contain cheche if dont have let it get from db just like boogie
    }
}
