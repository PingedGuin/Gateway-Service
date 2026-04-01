package com.app.service.security.auth.service;

import com.app.service.database.entitys.UserInfoEntity;
import com.app.service.database.repository.UserInfoRepository;
import com.app.service.dtos.register.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    UserInfoRepository userInfoRepository;

    RegisterService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public boolean handleRegister(RegisterRequest request) {
        if (userInfoRepository.existsByGmailAndUsername(request.getEmail(), request.getEmail())) return false;

        if (request.IsEmpty()) return false;

        userInfoRepository.save(new UserInfoEntity(request));

        return true;
    }
}
