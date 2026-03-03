package dev.dre.chatappserver.security.auth.service;

import dev.dre.chatappserver.database.entitys.UserSessionEntity;
import dev.dre.chatappserver.database.repository.UserInfoRepository;
import dev.dre.chatappserver.dtos.register.login.LoginDto;
import dev.dre.chatappserver.dtos.register.login.LoginResponseDto;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private final UserInfoRepository userInfoRepository;
    private final SessionService sessionService;
    private final TokenService tokenService;

    public LoginService(UserInfoRepository userInfoRepository, TokenService tokenService) {
        this.userInfoRepository = userInfoRepository;
        this.tokenService = tokenService;
        this.sessionService = new SessionService();
    }
    @Transactional
    public LoginResponseDto login(LoginDto request) {
        if (request == null || !userInfoRepository.isUserExist(request)) {
            return null;
        }
        // todo - add log instead of null return

        String token = tokenService.generateToken(request.getUsername());
        Cookie cookie = new Cookie("jwt", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60);
        //todo return user from db or caech (dto contain session id and token etc.) and make the changes on it

        sessionService.create(new UserSessionEntity(request.getUsername(),token,true));

        return new LoginResponseDto(token, request.getUsername(), cookie, 60 * 60);
    }

    public void logout(String token) {

    }

    public void refreshToken() {
    }
}
