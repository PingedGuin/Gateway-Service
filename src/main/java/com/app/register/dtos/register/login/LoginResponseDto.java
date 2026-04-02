package com.app.register.dtos.register.login;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String accessToken;
    private String username;
    private String expireDate;
    private String userId;
    private String sessionId;

    public LoginResponseDto(String accessToken, String username, String expireDate, String userId, String sessionId) {
        this.accessToken = accessToken;
        this.username = username;
        this.expireDate = expireDate;
        this.userId = userId;
        this.sessionId = sessionId;
    }
}