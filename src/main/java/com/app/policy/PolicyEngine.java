package com.app.policy;


import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
