package com.app.service.dtos.user;

import lombok.Data;

@Data
public class LoginRequest {
    String gmail;
    String password;
}
