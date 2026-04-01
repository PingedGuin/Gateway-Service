package com.app.user.controller;

import com.app.service.dtos.user.UpdateUserRequest;
import com.app.service.security.auth.service.TokenService;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/api/user/@me/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader(value = "Authorization") String token) {
        var userId = tokenService.extractUserId(token);
        //todo change this
        return ResponseEntity.ok("user info");
    }

    @PatchMapping("/api/user/@me/updateInfo")
    public ResponseEntity<?> updateUserInfo(@RequestHeader(value = "Authorization") String token, UpdateUserRequest request) {
        var userId = tokenService.extractUserId(token);

        return ResponseEntity.ok("user updated");
    }
}