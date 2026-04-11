package com.app.member.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MemberId implements Serializable {
    private Long userId;
    private Long guildId;
}