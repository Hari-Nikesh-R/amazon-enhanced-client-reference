package org.dosmartie.configuration;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.concurrent.atomic.AtomicReference;

public class Configuration {
    private static final AtomicReference<DynamoDbClient> dynamoDbClient = new AtomicReference<>();
    private static final AtomicReference<DynamoDbEnhancedClient> dynamoDbEnhancedClient = new AtomicReference<>();

    private Configuration() {
    }

    private static DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        if (dynamoDbEnhancedClient.get() == null) {
            dynamoDbEnhancedClient.compareAndSet(null, DynamoDbEnhancedClient.builder().dynamoDbClient(getDynamoDbClient()).build());
        }
        return dynamoDbEnhancedClient.get();
    }

    private static DynamoDbClient getDynamoDbClient() {
        if (dynamoDbClient.get() == null) {
            dynamoDbClient.compareAndSet(null, DynamoDbClient.builder()
                    .credentialsProvider(new AwsCredentialsProvider() {
                        @Override
                        public AwsCredentials resolveCredentials() {
                            return new AwsCredentials() {
                                @Override
                                public String accessKeyId() {
                                    return "YOUR_ACCESS_CODE";
                                }

                                @Override
                                public String secretAccessKey() {
                                    return "YOUR_SECRET_KEY";
                                }
                            };

                        }
                    })
                    .region(Region.of("us-east-1"))
                    .build());
        }
        return dynamoDbClient.get();
    }

    public static DynamoDbEnhancedClient initializeDynamoDbEnhancedClient() {
        return getDynamoDbEnhancedClient();
    }
}
