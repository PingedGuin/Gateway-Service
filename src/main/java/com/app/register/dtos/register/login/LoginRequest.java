package com.app.register.dtos.register.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@NotBlank
@Getter
public class LoginRequest {
    String email;
    String password;
}
