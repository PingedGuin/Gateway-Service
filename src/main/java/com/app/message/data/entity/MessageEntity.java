package com.app.message.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class MessageEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true,nullable = false)
    private Long messageId;

    private String content;

    private String guildId;

    private String channelId;

    private Long userId;

    private Instant createdAt;

}