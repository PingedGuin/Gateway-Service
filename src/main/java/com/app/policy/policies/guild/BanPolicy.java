package com.app.policy.policies.guild;

import com.app.policy.Action;
import com.app.policy.Policy;
import com.app.policy.PolicyContext;
import com.app.policy.annotation.PolicyType;

@PolicyType(action = Action.BAN)
public class BanPolicy implements Policy {
    @Override
    public boolean check(PolicyContext context) {

        return false;
    }
}
