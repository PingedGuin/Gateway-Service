package com.app.message.repository;

import com.app.message.data.entity.MessageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    MessageEntity findByMessageId(String messageId);
    @Query("""
SELECT m
FROM MessageEntity m
WHERE m.channelId = :channelId
ORDER BY m.createdAt DESC
""")
    List<MessageEntity> getGeneralMessages(
            @Param("channelId") String channelId,
            Pageable pageable
    );}
