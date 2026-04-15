package com.app.policy.policies.guild;

import com.app.policy.Action;
import com.app.policy.Policy;
import com.app.policy.PolicyContext;
import com.app.policy.annotation.PolicyType;

@PolicyType(action = Action.MANAGE_PERMISSIONS)
public class PermissionPolicy extends Policy {
    public void handle(PolicyContext context) {

    }
}
