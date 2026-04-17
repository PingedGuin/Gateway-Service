package com.app.message.service;

import com.app.policy.PolicyContext;
import com.app.policy.PolicyEngine;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final PolicyEngine policyEngine;

    public MessageService(PolicyEngine policyEngine) {
        this.policyEngine = policyEngine;
    }

    public void sendMessage(String message) {
        PolicyContext PolicyContext = new PolicyContext();
        policyEngine.check(PolicyContext );
    }
// 1. check if user banned
// 2. check membership (user in guild)
// 3. check channel access
// 4. check permissions (SEND_MESSAGE)
// 5. create message object
// 6. save message
// 7. publish event (WebSocket)

}
