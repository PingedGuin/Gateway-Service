package dev.dre.chatappserver.dtos.socket;

import lombok.Data;

@Data
public class SocketMessage {
        private String content;
        private String sender;
        private String room;

        // getters و setters
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }

        public String getSender() { return sender; }
        public void setSender(String sender) { this.sender = sender; }

        public String getRoom() { return room; }
        public void setRoom(String room) { this.room = room; }

}
