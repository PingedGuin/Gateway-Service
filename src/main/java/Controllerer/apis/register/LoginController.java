package Controllerer.apis.register;

import dev.dre.GetwayService.dtos.register.login.LoginDto;
import dev.dre.GetwayService.security.auth.service.LoginService;
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
