package com.app.policy;

import com.app.policy.annotation.PolicyType;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class PolicyRegistry {

    private final Map<Action, List<Policy>> policies = new ConcurrentHashMap<>();
    private final Set<Class<?>> registered = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public void register(Class<?> clazz) {

        if (!registered.add(clazz)) return;


        if (!clazz.isAnnotationPresent(PolicyType.class)) return;

        if (!Policy.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("Class must implement Policy: " + clazz.getName());
        }

        PolicyType annotation = clazz.getAnnotation(PolicyType.class);

        try {
            Action[] actions = annotation.action();
            Policy policy = (Policy) clazz.getDeclaredConstructor().newInstance();

            for (Action act : actions) {
                policies
                        .computeIfAbsent(act, k -> new CopyOnWriteArrayList<>())
                        .add(policy);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to register policy: " + clazz.getName(), e);
        }
    }

    public List<Policy> getPolicies(Action action) {
        return policies.getOrDefault(action, Collections.emptyList());
    }
}