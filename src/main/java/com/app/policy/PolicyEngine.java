package com.app.policy;


import java.util.List;

public class PolicyEngine {
    private final PolicyRegistry registry;

    public PolicyEngine(PolicyRegistry registry) {
        this.registry = registry;
    }

    public void check(PolicyContext context) {

        List<Policy> policies = registry.getPolicies(context.getAction());

        for (Policy policy : policies) {
            boolean passed = policy.check(context);

            if (!passed) {
                throw new RuntimeException(
                        "Policy check failed: " + policy.getClass().getSimpleName()
                );
            }
        }

    }

}
