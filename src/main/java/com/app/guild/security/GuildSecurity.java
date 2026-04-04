package com.app.guild.security;

import com.app.guild.data.dto.member.ManageMemberRequest;
import com.app.guild.data.permission.AccessLevel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class GuildSecurity {

    public boolean hasPermission(Authentication auth, ManageMemberRequest request, AccessLevel permission) {
        String username = auth.getName();


        return true;
    }
}