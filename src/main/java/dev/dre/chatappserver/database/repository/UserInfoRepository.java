package dev.dre.chatappserver.database.repository;

import dev.dre.chatappserver.database.entitys.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
    Optional<UserInfoEntity> findByUsername(String username, String password);
}
