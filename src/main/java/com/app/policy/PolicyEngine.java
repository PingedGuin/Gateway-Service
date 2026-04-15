package com.app.policy;

import com.app.policy.annotation.PolicyType;

public class PolicyEngine {
    private final PolicyType type;

    public PolicyEngine() {
        this.type = this.getClass().getAnnotation(PolicyType.class);

        var typeInfo = type.action();


    }
}
