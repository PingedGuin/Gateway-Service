package com.app.register.dtos.user;

import lombok.Data;

@Data
public class UpdateUserRequest {
    String username;
    String gmail;
    String password;
}
