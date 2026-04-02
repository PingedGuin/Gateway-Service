package com.app.user.controller;

import com.app.service.dtos.user.UpdateUserRequest;
import com.app.service.security.auth.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.app.user.service.UserService;

@RestController
public class UserInfoController {
    TokenService tokenService;
    UserService userService;
    public UserInfoController(TokenService tokenService , UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }
    @GetMapping("/api/user/@me")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        var userId = tokenService.extractUserId(authentication.getName());

        return ResponseEntity.ok("user info");
    }

    @PatchMapping("/api/user/@me")
    public ResponseEntity<?> updateUserInfo(Authentication authentication, UpdateUserRequest request) {
        var userId = tokenService.extractUserId(authentication.getName());

        return ResponseEntity.ok("user updated");
    }
    @DeleteMapping("/api/user/@me")
    public ResponseEntity<?> deleteUser(Authentication authentication) {
        String userId = authentication.getName();
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

}