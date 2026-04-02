package com.app.register.security.auth;

import com.app.register.database.entitys.UserSessionEntity;
import com.app.register.dtos.register.login.LoginDto;
import com.app.register.dtos.register.login.LoginResponseDto;
import com.app.user.repository.UserInfoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private final UserInfoRepository userInfoRepository; //todo idk if this correct or it should take the data from other table
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
        var userInfo = userInfoRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (userInfo == null) {
            return null;
        }
        // todo - add log instead of null return
        var username = request.getUsername();
        var session = sessionService.getOrCreateSession(new UserSessionEntity(request.getUsername(),true));
        var accasToken = tokenService.generateAccessToken(username,session.getSessionId(),session.getExpiresAt());
        return new LoginResponseDto(accasToken, request.getUsername());
    }

    public void logout(String token) {

    }

    public void refreshToken() {
    }

}
