package dev.dre.chatappserver.dtos.register.login;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String accessToken;
    private String username;
    public LoginResponseDto(String accessToken, String username) {
        this.username = username;
        this.accessToken = accessToken;
    }
}