package com.app.message.data.dto;

import lombok.Data;

@Data
public class LoadMessagesRequest {
    private String channelId;
    private int limit;
    private int offset;
}
