package com.app.guild.controller;

import com.app.guild.data.dto.CreateChannelRequest;
import com.app.guild.data.dto.editChannelRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuildChannelController {
    @PostMapping("/api/channels")
    public ResponseEntity<?> createChannel(Authentication auth,@RequestBody CreateChannelRequest request){

        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/api/channels")
    public ResponseEntity<?> deleteChannel(Authentication auth, editChannelRequest request){

        return ResponseEntity.ok().build();
    }
    @PostMapping("/api/channels")
    public ResponseEntity<?> editChannel(Authentication auth,editChannelRequest request){
        return ResponseEntity.ok().build();
    }
}
