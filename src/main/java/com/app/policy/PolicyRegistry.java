package com.app.policy;

import com.app.policy.annotation.PolicyType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PolicyRegistry {

    private final Map<Action, List<Policy>> policiesByAction = new EnumMap<>(Action.class);

    private final List<Policy> policies;

    public PolicyRegistry(List<Policy> policies) {
        this.policies = policies;
    }

    @PostConstruct
    public void init() {

        for (Policy policy : policies) {

            PolicyType meta = policy.getClass().getAnnotation(PolicyType.class);
            if (meta == null) continue;

            for (Action action : meta.action()) {
                policiesByAction
                        .computeIfAbsent(action, k -> new ArrayList<>())
                        .add(policy);
            }
        }
    }

    public List<Policy> getPolicies(Action action) {
        return policiesByAction.getOrDefault(action, Collections.emptyList());
    }
}