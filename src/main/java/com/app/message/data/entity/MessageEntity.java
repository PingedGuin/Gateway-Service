package com.app.message.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class MessageEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private String guildId;

    private String channelId;

    private String userId;

    private Instant createdAt;

}