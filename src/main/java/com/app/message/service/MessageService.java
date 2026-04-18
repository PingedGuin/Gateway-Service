package com.app.message.service;

import com.app.message.data.dto.ChatMessageDto;
import com.app.policy.PolicyContext;
import com.app.policy.PolicyEngine;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final PolicyEngine policyEngine;
    private final WebSocketService webSocketService;
    private final MessageRepository messageRepository;
    public MessageService(PolicyEngine policyEngine, WebSocketService webSocketService) {
        this.policyEngine = policyEngine;
        this.webSocketService = webSocketService;
    }

    public void handleSendMsgReq(PolicyContext context) {
        // policyEngine.check(context);

        webSocketService.sendMessage(new ChatMessageDto(context));
    }
// 1. check if user banned
// 2. check membership (user in guild)
// 3. check channel access
// 4. check permissions (SEND_MESSAGE)
// 5. create message object
// 6. save message
// 7. publish event (WebSocket)

}
