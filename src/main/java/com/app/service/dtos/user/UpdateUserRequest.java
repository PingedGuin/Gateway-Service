package com.app.service.dtos.user;

import lombok.Data;

@Data
public class UpdateUserRequest {
    String username;
    String gmail;
    String password;
}
