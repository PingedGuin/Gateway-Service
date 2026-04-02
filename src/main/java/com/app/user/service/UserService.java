package com.app.user.service;

import com.app.service.dtos.user.UserInfo;
import com.app.user.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserInfoRepository userInfoRepository;

    public UserService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    //todo add cheche tho

    public UserInfo getUserInfo(String username) {
        return new UserInfo("username", "id", "gmail");
    }
}
