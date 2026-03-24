package Controllerer.apis.user;

import dev.dre.GetwayService.security.auth.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
    TokenService tokenService;
    public UserInfoController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @GetMapping("/api/user/@me/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader(value = "Authorization") String token) {
        var userId = tokenService.extractUserId(token);
        //todo change this
        return null;
    }
}