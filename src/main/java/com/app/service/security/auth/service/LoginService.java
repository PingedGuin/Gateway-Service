package com.app.service.security.auth.service;

import com.app.service.database.entitys.UserSessionEntity;
import com.app.service.database.repository.UserInfoRepository;
import com.app.service.dtos.register.login.LoginDto;
import com.app.service.dtos.register.login.LoginResponseDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private final UserInfoRepository userInfoRepository;
    private final SessionService sessionService;
    private final TokenService tokenService;

    public LoginService(UserInfoRepository userInfoRepository, TokenService tokenService, SessionService sessionService) {
        this.userInfoRepository = userInfoRepository;
        this.tokenService = tokenService;
        this.sessionService = sessionService;
    }

    @Transactional
    @Cacheable("userInfo")
    public LoginResponseDto login(LoginDto request) {
        var info = userInfoRepository.findByUsername(request.getUsername(), request.getPassword()).orElse(null);
        if (info == null) {
            return null;
        }
        // todo - add log instead of null return

        var session = sessionService.getOrCreateSession(new UserSessionEntity(request.getUsername(),true));
        var accasToken = tokenService.genrateAccessToken(info.getUsername(),session.getSessionId(),session.getExpiresAt());
        return new LoginResponseDto(accasToken, request.getUsername());
    }

    public void logout(String token) {

    }

    public void refreshToken() {
    }
}
