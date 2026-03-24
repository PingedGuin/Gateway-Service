package dev.dre.GetwayService.security.auth.service;

import dev.dre.GetwayService.database.entitys.UserInfoEntity;
import dev.dre.GetwayService.database.repository.UserInfoRepository;
import dev.dre.GetwayService.dtos.register.RegisterRequest;
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
