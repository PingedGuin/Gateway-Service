package com.app.websocket;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

@Data
public class Session {
    Long sessionId;
    WebSocketSession session;
    String username;
    Long permission;

    public Session(WebSocketSession session) {
        this.session = session;
    }
}
