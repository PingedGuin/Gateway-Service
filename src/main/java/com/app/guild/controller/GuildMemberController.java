package com.app.guild.controller;

import com.app.guild.data.dto.member.ManageMemberRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("member/manage")
public class GuildMemberController {
    @PostMapping("join")
    public ResponseEntity<?> joinGuild(Authentication auth, @RequestBody ManageMemberRequest request) {
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("leave/{guildId}")
    public ResponseEntity<?> leaveGuild(Authentication auth, @RequestParam ManageMemberRequest request) {

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("@guildSecurity.hasPermission(authentication, #request, T(com.app.guild.data.permission.AccessLevel).BAN_MEMBER)")
    @PatchMapping("ban")
    public ResponseEntity<?> banMember(Authentication auth, @RequestBody ManageMemberRequest request) {
        String username = auth.getName();

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("@guildSecurity.hasPermission(authentication, #request, T(com.app.guild.data.permission.AccessLevel).BAN_MEMBER)")
    @PatchMapping("unban")
    public ResponseEntity<?> unbanMember(Authentication auth, @RequestBody ManageMemberRequest request) {

        return ResponseEntity.ok().build();
    }
    @PreAuthorize("@guildSecurity.hasPermission(authentication, #request, T(com.app.guild.data.permission.AccessLevel).KICK_MEMBER)")
    @DeleteMapping("kick")
    public ResponseEntity<?> kickMember(Authentication auth, @RequestBody ManageMemberRequest request) {
        String username = auth.getName();

        return ResponseEntity.ok().build();
    }
    @PreAuthorize("@guildSecurity.hasPermission(authentication, #request, T(com.app.guild.data.permission.AccessLevel).MUTE_MEMBER)")
    @PostMapping("mute")
    public ResponseEntity<?> muteMember(Authentication auth, @RequestBody ManageMemberRequest request) {
        String username = auth.getName();

        return ResponseEntity.ok().build();
    }
    @PreAuthorize("@guildSecurity.hasPermission(authentication, #request, T(com.app.guild.data.permission.AccessLevel).MUTE_MEMBER)")
    @PostMapping("unmute")
    public ResponseEntity<?> unmuteMember(Authentication auth, @RequestBody ManageMemberRequest request) {
        String username = auth.getName();

        return ResponseEntity.ok().build();
    }
    @PreAuthorize("@guildSecurity.hasPermission(authentication, #request, T(com.app.guild.data.permission.AccessLevel).DEAFEN_MEMBER)")
    @PostMapping("deafen")
    public ResponseEntity<?> deafenMember(Authentication auth, @RequestBody ManageMemberRequest request) {
        String username = auth.getName();

        return ResponseEntity.ok().build();
    }
    @PreAuthorize("@guildSecurity.hasPermission(authentication, #request, T(com.app.guild.data.permission.AccessLevel).DEAFEN_MEMBER)")
    @PatchMapping("undeafen")
    public ResponseEntity<?> undeafenMember(Authentication auth, @RequestBody ManageMemberRequest request) {
        String username = auth.getName();

        return ResponseEntity.ok().build();
    }
    @PreAuthorize("@guildSecurity.hasPermission(authentication, #request, T(com.app.guild.data.permission.AccessLevel).CHANGE_NICKNAME)")
    @PatchMapping("changeNickname")
    public ResponseEntity<?> changeNickname(Authentication auth, @RequestBody ManageMemberRequest request) {
        String username = auth.getName();


        return ResponseEntity.ok().build();
    }

}
