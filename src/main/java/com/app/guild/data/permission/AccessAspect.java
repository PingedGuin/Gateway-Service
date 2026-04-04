package com.app.guild.data.permission;

import com.app.guild.data.dto.GuildRequest;
import com.app.guild.data.dto.member.ManageMemberRequest;
import com.app.guild.security.GuildSecurity;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AccessAspect {
    private final GuildSecurity guildSecurity;

    @Before("@annotation(requiresAccess)")
    public void checkAccess(JoinPoint joinPoint, RequiresAccess requiresAccess) {
        Object[] args = joinPoint.getArgs();

        Authentication auth = null;
        GuildRequest request = null;

        for (Object arg : args) {
            if (arg instanceof Authentication) {
                auth = (Authentication) arg;
            } else if (arg instanceof GuildRequest) {
                request = (GuildRequest) arg;
            }
        }

        AccessLevel required = requiresAccess.value();

        boolean allowed = guildSecurity.hasPermission(auth, request, required);

        if (!allowed) {
            throw new RuntimeException("Access Denied");
        }
    }
}
