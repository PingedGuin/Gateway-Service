package com.app.guild.permission.engine.user;

import com.app.guild.data.dto.GuildOperationRequest;
import com.app.guild.security.GuildSecurity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AccessAspect {
    private final GuildSecurity guildSecurity;

    @Before("@annotation(requiresAccess)")
    public void checkAccess(JoinPoint joinPoint, RequiresAccess requiresAccess) {
        Object[] args = joinPoint.getArgs();

        Authentication auth = null;
        GuildOperationRequest request = null;

        for (Object arg : args) {
            if (arg instanceof Authentication) {
                auth = (Authentication) arg;
            } else if (arg instanceof GuildOperationRequest) {
                request = (GuildOperationRequest) arg;
            }
        }

        AccessLevel required = requiresAccess.value();

        if (auth == null || request == null) {
            throw new RuntimeException("Invalid access context");
        }

        boolean allowed = guildSecurity.hasPermission(auth, request, required);

        if (!allowed) {
            log.warn("access denied for user {} on guild {}", auth.getName(), request.getGuildId());
            throw new RuntimeException("access denied");
        }
    }
}
