package com.app.policy;


import com.app.policy.annotation.PolicyType;
import com.app.policy.policies.guild.BanPolicy;

import java.util.*;

public class PolicyRegistry {

    private final Map<Action, List<Policy>> policies = new HashMap<>();
    private final Set<Class<?>> registered = new HashSet<>();
    Map<Action,Policy> policyMap = new HashMap<>();

    public PolicyRegistry() {
        policyMap.put(Action.BAN,new BanPolicy());
    }
    public void register(Class<?> clazz) {

        if (registered.contains(clazz)) return;

        if (!clazz.isAnnotationPresent(PolicyType.class)) return;

        PolicyType annotation = clazz.getAnnotation(PolicyType.class);

        try {
            Policy policy = (Policy) clazz.getDeclaredConstructor().newInstance();

            var action = annotation.action();

            policies.computeIfAbsent(action, k -> new ArrayList<>())
                    .add(policy);

            registered.add(clazz);

        } catch (Exception e) {
            throw new RuntimeException("Failed to register policy: " + clazz.getName(), e);
        }
    }

    public List<Policy> getPolicies(Action action) {
        return policies.getOrDefault(action, List.of());
    }
}