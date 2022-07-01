package co.com.mercadolibre.dynamodb.config;

import co.com.mercadolibre.dynamodb.entity.Specie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

@Configuration
public class DynamoDBConfig {

    @Value("${aws.dynamodb.table}")
    private String tableName;

    @Value("${aws.credentials.access.key}")
    private String accessKey;

    @Value("${aws.credentials.secret.access.key}")
    private String secretAccessKey;

    @Bean
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
