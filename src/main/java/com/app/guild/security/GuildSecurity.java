package com.app.guild.security;

import com.app.guild.data.dto.member.ManageMemberRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class GuildSecurity {

    public boolean hasPermission(Authentication auth, ManageMemberRequest request, String permission) {
        String username = auth.getName();

        // 1. تجيب العضو من الداتابيس
        // 2. تجيب roles الخاصة بالعضو داخل هذا guild
        // 3. تشوف هل عنده permission أو لا
        // ممكن ترجع true أو false
        return true;
    }
}