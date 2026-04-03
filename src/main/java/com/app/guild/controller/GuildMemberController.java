package com.app.guild.controller;

import com.app.guild.data.dto.member.ManageMemberRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class GuildMemberController {
    @PostMapping("api/guild/member/manage/join")
    public void joinGuild(Authentication auth,@RequestBody ManageMemberRequest request){

    }
    @DeleteMapping("api/guild/member/manage/leave")
    public void leaveGuild(Authentication auth, @RequestBody ManageMemberRequest request){

    }
    @PatchMapping("api/guild/member/manage/ban")
    public void banMember(Authentication auth, @RequestBody ManageMemberRequest request){

    }
    @PatchMapping("api/guild/member/manage/unban")
    public void unbanMember(Authentication auth, @RequestBody ManageMemberRequest request){

    }
    @DeleteMapping("api/guild/member/manage/kick")
    public void kickMember(Authentication auth, @RequestBody ManageMemberRequest request){

    }
    @PostMapping("api/guild/member/manage/mute")
    public void muteMember(Authentication auth, @RequestBody ManageMemberRequest request){

    }
    @PostMapping("api/guild/member/manage/unmute")
    public void unmuteMember(Authentication auth, @RequestBody ManageMemberRequest request){

    }
    @PostMapping("api/guild/member/manage/deafen")
    public void deafenMember(Authentication auth, @RequestBody ManageMemberRequest request){

    }
    @PatchMapping("api/guild/member/manage/undeafen")
    public void undeafenMember(Authentication auth, @RequestBody ManageMemberRequest request){

    }
    @PatchMapping("api/guild/member/manage/changeNickname")
    public void changeNickname(Authentication auth, @RequestBody ManageMemberRequest request){

    }

}
