package com.app.policy.policies.member;

import com.app.message.data.dto.ChatMessageDto;
import com.app.policy.Policy;

public class MembershipPolicy implements Policy {
    @Override
    public boolean check(ChatMessageDto context) {

        return false;
    }
}
