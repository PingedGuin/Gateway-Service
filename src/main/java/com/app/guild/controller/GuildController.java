package com.app.guild.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuildController {
    @PostMapping("/api/guild")
    public ResponseEntity<?> createGuild(Authentication auth) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteGuild(Authentication auth) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editGuildSettings(Authentication auth) {
        return ResponseEntity.ok().build();
    }
}
