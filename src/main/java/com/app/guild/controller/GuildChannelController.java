package com.app.guild.controller;

import com.app.guild.data.dto.channel.CreateChannelRequest;
import com.app.guild.data.dto.channel.EditChannelRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuildChannelController {
    @PostMapping("/api/guild/channel")
    public ResponseEntity<?> createChannel(Authentication auth,@RequestBody CreateChannelRequest request){

        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/api/guild/channel")
    public ResponseEntity<?> deleteChannel(Authentication auth,@RequestBody EditChannelRequest request){

        return ResponseEntity.ok().build();
    }
    @PostMapping("/api/guild/channel")
    public ResponseEntity<?> editChannel(Authentication auth,@RequestBody EditChannelRequest request){
        return ResponseEntity.ok().build();
    }
}
