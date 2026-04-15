package com.app.policy.annotation;

import com.app.policy.Action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PolicyType {
        String userId();
        String guildId();
        String channelId();
        Action action();
}
