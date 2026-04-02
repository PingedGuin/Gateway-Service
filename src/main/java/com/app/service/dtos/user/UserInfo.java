package com.app.service.dtos.user;

import lombok.Data;

@Data
public class UserInfo {
    String username;
    String gmail;
    String password;

    public UserInfo(String username, String gmail, String password) {
        this.username = username;
        this.gmail = gmail;
        this.password = password;
    }
}