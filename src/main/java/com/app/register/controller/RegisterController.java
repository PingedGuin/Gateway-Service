package com.app.register.controller;

import com.app.register.dtos.register.RegisterRequest;
import com.app.register.security.auth.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    RegisterService registerService;
    public RegisterController(RegisterService registerService){
        this.registerService =registerService;
    }
    @PostMapping("/api/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (registerService.handleRegister(request)){
            return ResponseEntity.badRequest().body("user already exist");
        }

        return ResponseEntity.ok().body("new user registered");
    }
}
