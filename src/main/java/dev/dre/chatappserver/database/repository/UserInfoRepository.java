package dev.dre.chatappserver.database.repository;

import dev.dre.chatappserver.database.UserInfo;
import dev.dre.chatappserver.dtos.register.login.LoginResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository <UserInfo, Long> {
    Optional<LoginResponseDto> findByUsername(String username);
    Optional<LoginResponseDto> checkUserInfo(String username, String password);
}
