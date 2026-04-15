package com.app.policy.policies.guild;

import com.app.policy.Action;
import com.app.policy.Policy;
import com.app.policy.PolicyContext;
import com.app.policy.Priority;
import com.app.policy.annotation.PolicyType;

@PolicyType(action = Action.MANAGE_PERMISSIONS,priority = Priority.MEDIUM)
public class PermissionPolicy implements Policy {
    public boolean check(PolicyContext context) {

        return false;
    }
}
