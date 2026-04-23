package com.app.policy.policies.guild;

import com.app.message.data.dto.ChatMessageDto;
import com.app.policy.Action;
import com.app.policy.Policy;
import com.app.policy.Priority;
import com.app.policy.annotation.PolicyType;

@PolicyType(action = Action.BAN,priority = Priority.MEDIUM)
public class BanPolicy implements Policy {
    @Override
    public boolean check(ChatMessageDto context) {

        return false;
    }
}
