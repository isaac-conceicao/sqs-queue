package devtrail.java.sqsqueue;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class SqsProducer {

    public static void sendMessage(SqsClient sqsClient, String message, String queueUrl) {
        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .delaySeconds(0)
                .build();

        sqsClient.sendMessage(sendMsgRequest);
        System.out.println("Mensagem enviada: " + message);
    }
}

