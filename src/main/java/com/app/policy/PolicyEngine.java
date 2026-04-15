package com.app.policy;


public class PolicyEngine {
    private final PolicyRegistry registry;

    public PolicyEngine(PolicyRegistry registry) {
        this.registry = registry;
    }

    public void check(PolicyContext context) {

    }

}
