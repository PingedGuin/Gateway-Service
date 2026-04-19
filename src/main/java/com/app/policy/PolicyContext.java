package com.app.policy;

import lombok.Getter;

@Getter
public class PolicyContext {
    private Long userId;
    private String guildId;
    private String channelId;
    private String permission;
    private Action action;
    private String content;
}
