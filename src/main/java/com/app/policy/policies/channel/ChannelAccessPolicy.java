package com.app.policy.policies.channel;

import com.app.policy.Action;
import com.app.policy.Policy;
import com.app.policy.PolicyContext;
import com.app.policy.annotation.PolicyType;

@PolicyType(action = Action.MANAGE_CHANNELS)
public class ChannelAccessPolicy implements Policy {
    @Override
    public void check(PolicyContext context) {

    }
}
