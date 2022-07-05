package co.com.mercadolibre.dynamodb.config;

import co.com.mercadolibre.dynamodb.entity.Specie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.net.URI;

@Configuration
public class DynamoDBConfig {

    @Value("#{environment.DYNAMODB_TABLE}")
    private String tableName;

    @Value("#{environment.ACCESS_KEY_ID}")
    private String accessKey;

    @Value("#{environment.ACCESS_SECRET_KEY}")
    private String secretAccessKey;

    @Bean
    @Profile({"local"})
    public DynamoDbAsyncClient amazonDynamoDB(@Value("#{environment.AWS_REGION}") String region,
                                              @Value("#{environment.AWS_DYNAMODB_END_POINT}") String endPoint,
                                              @Value("#{environment.AWS_DYNAMODB_TABLE_NAME}") String tableName) {
        this.tableName = tableName;
        return DynamoDbAsyncClient.builder()
                .endpointOverride(URI.create(endPoint))
                .region(Region.of(region))
                .build();
    }

    @Bean
    @Profile({"dev"})
    public DynamoDbAsyncClient amazonDynamoDBAsync() {
        return DynamoDbAsyncClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretAccessKey))
                .build();
    }

    @Bean
    public DynamoDbEnhancedAsyncClient getDynamoDbEnhancedAsyncClient(DynamoDbAsyncClient client) {
        return DynamoDbEnhancedAsyncClient.builder()
                .dynamoDbClient(client)
                .build();
    }

    @Bean
    public DynamoDbAsyncTable<Specie> dynamoDbAsyncTable(DynamoDbEnhancedAsyncClient asyncClient) {
        return asyncClient.table(tableName, TableSchema.fromBean(Specie.class));
    }

}
