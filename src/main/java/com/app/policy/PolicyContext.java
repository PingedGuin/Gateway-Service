package com.app.policy;

import lombok.Getter;

@Getter
public class PolicyContext {
    private String userId;
    private String guildId;
    private String channelId;
    private String permission;
    private Action action;
    private String content;
}
