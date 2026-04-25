package com.app.guild.controller;

import com.app.guild.data.dto.channel.CreateChannelRequest;
import com.app.guild.data.dto.channel.EditChannelRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/channel")
public class GuildChannelController {
    @PostMapping()
    public ResponseEntity<?> createChannel(Authentication auth,@RequestBody CreateChannelRequest request){

        return ResponseEntity.ok().build();
    }
    @DeleteMapping({"/{channelId}"})
    public ResponseEntity<?> deleteChannel(Authentication auth, @PathVariable String channelId){

        return ResponseEntity.ok().build();
    }
    @PatchMapping()
    public ResponseEntity<?> editChannel(Authentication auth,@RequestBody EditChannelRequest request){
        return ResponseEntity.ok().build();
    }
}
