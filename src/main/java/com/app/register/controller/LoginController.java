package com.app.register.controller;

import com.app.register.dtos.register.login.LoginRequest;
import com.app.register.security.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {

        var responseDto = authService.login(request);

        Cookie cookie = new Cookie("jwt", responseDto.getAccessToken());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60);
        cookie.setSecure(true);

        response.addHeader("Set-Cookie",
                String.format("jwt=%s; Path=/; HttpOnly; Secure; SameSite=None; Max-Age=%d",
                        responseDto.getAccessToken(),
                        60 * 60 * 24 * 7));

        return ResponseEntity.ok(responseDto);
    }
}
