package com.app.register.security.auth;

import com.app.user.data.entity.UserInfoEntity;
import com.app.user.repository.UserInfoRepository;
import com.app.register.dtos.register.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final PasswordEncoderService passwordEncoderService;
    UserInfoRepository userInfoRepository;

    RegisterService(UserInfoRepository userInfoRepository, PasswordEncoderService passwordEncoderService) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoderService = passwordEncoderService;
    }

    public boolean handleRegister(RegisterRequest request) {
        if (userInfoRepository.existsByEmailAndUsername(request.getEmail(), request.getUsername())) return false;

        if (request.IsEmpty()) return false;
        var passEncode = passwordEncoderService.encode(request.getPassword());
        request.setPassword(passEncode);
        userInfoRepository.save(new UserInfoEntity(request));

        return true;
    }
}
