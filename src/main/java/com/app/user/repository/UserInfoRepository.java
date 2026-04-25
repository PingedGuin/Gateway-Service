package com.app.user.repository;

import com.app.user.data.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
    UserInfoEntity findByUsernameAndPassword(String username, String password);

    Optional<UserInfoEntity> findByEmail(String email);

    Boolean existsAllByEmailAndPassword(String email, String password);

    boolean existsByEmailAndUsername(String gmail, String username);

    boolean existsByEmail(String gmail);

    boolean existsByUsername(String username);
}
