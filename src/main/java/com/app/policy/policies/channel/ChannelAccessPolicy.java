package com.app.policy.policies.channel;

import com.app.message.data.dto.ChatMessageDto;
import com.app.policy.Action;
import com.app.policy.Policy;
import com.app.policy.PolicyContext;
import com.app.policy.Priority;
import com.app.policy.annotation.PolicyType;

@PolicyType(action = Action.MANAGE_CHANNELS, priority = Priority.HIGH)
public class ChannelAccessPolicy implements Policy {
    @Override
    public boolean check(ChatMessageDto context) {

        return false;
    }
}
