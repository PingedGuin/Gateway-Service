package com.app.register.dtos.register.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@NotBlank
@Getter
public class LoginDto {
    String username;
    String password;
}
