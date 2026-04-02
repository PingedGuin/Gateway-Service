package com.app.user.repository;

import com.app.user.data.dto.UserInfo;
import com.app.user.data.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
    UserInfoEntity findByUsernameAndPassword(String username, String password);
    Optional<UserInfoEntity> findByGmail(String gmail);
    boolean existsByGmailAndUsername(String gmail, String username);
    boolean existsByGmail(String gmail);
    boolean existsByUsername(String username);
}
