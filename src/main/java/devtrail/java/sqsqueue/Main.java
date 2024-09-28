package devtrail.java.sqsqueue;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

public class Main {

    private static final String message = "Olá, esta é uma mensagem de teste no LocalStack!";

    public static void main(String[] args) {

        //builds the SQS client pointing to LocalStack
        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)  // Região qualquer, já que LocalStack é local
                .endpointOverride(URI.create("http://localhost:4566"))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("dummy", "dummy")
                ))
                .build();

        // sends the message
        SqsProducer.sendMessage(sqsClient, message);

        // receives and process the message
        SqsConsumer.receiveMessages(sqsClient);

        // closes the client
        sqsClient.close();
    }
}
