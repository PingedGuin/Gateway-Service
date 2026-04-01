package com.app.service.dtos.socket;

import lombok.Data;

@Data
public class SocketMessage {
    private String content;
        private String sender;
        private String room;
}
