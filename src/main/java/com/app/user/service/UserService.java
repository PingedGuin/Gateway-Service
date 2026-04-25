package com.app.user.service;

import com.app.user.data.dto.UserInfo;
import com.app.user.data.entity.UserInfoEntity;
import com.app.user.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserInfoRepository userInfoRepository;

    public UserService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    //todo add cheche tho

    public UserInfo getUserInfo(String email) {
        UserInfoEntity entity = userInfoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return entityToDto(entity);
    }

    private UserInfo entityToDto(UserInfoEntity entity) {
        return new UserInfo(entity.getUsername(), entity.getEmail());
    }

}
