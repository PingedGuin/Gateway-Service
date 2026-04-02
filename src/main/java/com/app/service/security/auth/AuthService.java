package com.app.service.security.auth;

import com.app.service.database.entitys.UserSessionEntity;
import com.app.service.dtos.user.LoginRequest;
import com.app.user.data.entity.UserInfoEntity;
import com.app.user.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class AuthService {
    private final UserInfoRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final TokenService tokenService;
    private final SessionService sessionService;

    AuthService(UserInfoRepository userInfoRepository, PasswordEncoderService passwordEncoderService, TokenService tokenService, SessionService sessionService) {
        this.userRepository = userInfoRepository;
        this.passwordEncoderService = passwordEncoderService;
        this.tokenService = tokenService;
        this.sessionService = sessionService;
    }

    public String login(LoginRequest request) {
        UserInfoEntity user = userRepository.findByGmail(request.getGmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoderService.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        UserSessionEntity session = new UserSessionEntity();
        session.setUserId(user.getId().toString());
        session.setSessionId(UUID.randomUUID().toString());

        session = sessionService.getOrCreateSession(session);
        LocalDate expireDate = LocalDate.now().plusDays(7);
        return tokenService.generateAccessToken(
                user.getId().toString(),
                session.getSessionId(),
                expireDate.toString()
        );
    }
}
