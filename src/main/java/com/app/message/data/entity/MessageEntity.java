package com.app.message.data.entity;

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

    private String content;

    private String guildId;

    private String channelId;

    private Long userId;

    private Instant createdAt;

}