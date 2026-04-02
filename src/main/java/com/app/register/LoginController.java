package com.app.register;

import com.app.service.dtos.register.login.LoginDto;
import com.app.service.security.auth.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto request, HttpServletResponse response) {

        var responseDto = loginService.login(request);
        if (responseDto == null) return ResponseEntity.badRequest().body("user not found");

        Cookie cookie = new Cookie("jwt", responseDto.getAccessToken());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
        return ResponseEntity.ok().body("logged in");

    }
}
