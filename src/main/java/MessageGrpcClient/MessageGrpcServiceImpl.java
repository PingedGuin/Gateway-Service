package MessageGrpcClient;

import lombok.extern.slf4j.Slf4j;
import message.Message;
import message.MessageServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageGrpcServiceImpl extends MessageServiceGrpc.MessageServiceImplBase {
    @GrpcClient("message-service")
    private MessageServiceGrpc.MessageServiceBlockingStub messageStub;

    public void sendMessage(String content) {
        Message.SendMessageRequest request = Message.SendMessageRequest.newBuilder()
                .setChannelId("general")
                .setUserId("123")
                .setContent(content)
                .build();

        Message.MessageResponse response = messageStub.sendMessage(request);

        log.info("Request sent to Message service {}",request);
    }
}
