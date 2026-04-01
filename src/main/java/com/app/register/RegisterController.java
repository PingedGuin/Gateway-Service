package com.app.register;

import com.app.service.dtos.register.RegisterRequest;
import com.app.service.security.auth.service.RegisterService;
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
