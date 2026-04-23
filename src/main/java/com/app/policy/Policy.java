package com.app.policy;


import com.app.message.data.dto.ChatMessageDto;

public interface Policy {
    public boolean check(ChatMessageDto context);

}
