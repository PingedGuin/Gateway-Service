package com.app.guild.permission.engine.user;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresAccess {
    AccessLevel value();
}
