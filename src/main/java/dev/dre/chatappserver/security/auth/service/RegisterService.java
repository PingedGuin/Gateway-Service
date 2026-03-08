package dev.dre.chatappserver.security.auth.service;

import dev.dre.chatappserver.database.entitys.UserInfoEntity;
import dev.dre.chatappserver.database.repository.UserInfoRepository;
import dev.dre.chatappserver.dtos.register.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    UserInfoRepository userInfoRepository;

    RegisterService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public boolean handleRegister(RegisterRequest request) {
        if (userInfoRepository.findByGmailAndUsername(request.getEmail(), request.getEmail())) return false;

        if (request.IsEmpty()) return false;

        userInfoRepository.save(new UserInfoEntity(request));

        return true;
    }
}
