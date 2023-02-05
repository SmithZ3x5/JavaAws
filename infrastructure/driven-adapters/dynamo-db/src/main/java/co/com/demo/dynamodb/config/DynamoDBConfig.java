package co.com.demo.dynamodb.config;

import co.com.demo.dynamodb.DynamoDBTemplateAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.metrics.MetricPublisher;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import org.reactivecommons.utils.ObjectMapper;

import java.net.URI;

@Configuration
//@ComponentScan(basePackages = "co.com.demo.dynamodb")
public class DynamoDBConfig {
    private final String dynamoDbEndPointUrl;

    public DynamoDBConfig(@Value("${aws.dynamodb.endpoint}") String dynamoDbEndPointUrl) {
        this.dynamoDbEndPointUrl = dynamoDbEndPointUrl;
    }

    @Bean
    @Profile({"local"})
    public DynamoDbAsyncClient amazonDynamoDB(MetricPublisher publisher) {
        return DynamoDbAsyncClient.builder()
                .credentialsProvider(ProfileCredentialsProvider.create("default"))
                .endpointOverride(URI.create(dynamoDbEndPointUrl))
                .overrideConfiguration(o -> o.addMetricPublisher(publisher))
                .build();
    }

    @Bean
    @Profile({"dev", "cer", "pdn"})
    public DynamoDbAsyncClient amazonDynamoDBAsync(MetricPublisher publisher) {
        return DynamoDbAsyncClient.builder()
                .overrideConfiguration(o -> o.addMetricPublisher(publisher))
                .build();
    }

    @Bean
    public DynamoDbAsyncClient getDynamoDbAsyncClient() {
        return DynamoDbAsyncClient.builder()
                .endpointOverride(URI.create(dynamoDbEndPointUrl))
                .build();
    }

    @Bean
    public DynamoDbEnhancedAsyncClient getDynamoDbEnhancedAsyncClient(DynamoDbAsyncClient client) {
        return DynamoDbEnhancedAsyncClient.builder()
                .dynamoDbClient(client)
                .build();
    }

    /*@Bean
    public DynamoDBTemplateAdapter getDynamoDBTemplateAdapter(ObjectMapper mapper){
        return new DynamoDBTemplateAdapter(getDynamoDbEnhancedAsyncClient(getDynamoDbAsyncClient()), mapper);
    }*/

}
