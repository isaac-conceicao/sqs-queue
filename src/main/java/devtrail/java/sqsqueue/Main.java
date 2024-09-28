package devtrail.java.sqsqueue;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

public class Main {

    // URL to LocalStack SQS
    private static final String QUEUE_URL = "http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-queue";
    private static final String MESSAGE = "Olá, esta é uma mensagem de teste no LocalStack!";

    public static void main(String[] args) {

        //builds the SQS client pointing to LocalStack
        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(URI.create("http://localhost:4566"))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("user", "password") //
                ))
                .build();

        // sends the message
        SqsProducer.sendMessage(sqsClient, MESSAGE, QUEUE_URL);

        // receives and process the message
        SqsConsumer.receiveMessages(sqsClient, QUEUE_URL);

        // closes the client
        sqsClient.close();
    }
}
