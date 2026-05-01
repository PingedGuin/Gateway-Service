package com.app.message.controller;

import com.app.message.data.dto.chat.command.JoinRequest;
import com.app.message.data.dto.chat.command.LeaveRequest;
import com.app.message.data.dto.chat.command.TypingRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ChatController {
    @MessageMapping("/channel.join")
    public void join(@RequestBody JoinRequest req) {
    }
    @MessageMapping("/channel.leave")
    public void leave(@RequestBody LeaveRequest req) {
    }
    @MessageMapping("/channel.typing")
    public void typing(@RequestBody TypingRequest req) {
    }
}
