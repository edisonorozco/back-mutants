package co.com.mercadolibre.dynamodb.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DynamoDbConfigLocalStackTest {

    @DisplayName("JUnit test for build AmazonDynamoDB configuration")
    @Test
    void buildAmazonDynamoDBAsync() {
        DynamoDBConfig dynamoDbConfigLocalStack = new DynamoDBConfig();
        assertNotNull(dynamoDbConfigLocalStack.amazonDynamoDBAsync());
    }

    @DisplayName("JUnit test for build dynamoDbEnhancedAsyncClient  configuration")
    @Test
    void buildDynamoDbEnhancedAsyncClientLocalStack3() {
        DynamoDBConfig dynamoDbConfigLocalStack = new DynamoDBConfig();
        assertNotNull(dynamoDbConfigLocalStack.getDynamoDbEnhancedAsyncClient(
                dynamoDbConfigLocalStack.amazonDynamoDBAsync(
                )));
    }

    @DisplayName("JUnit test for build dynamoDbAsyncTable configuration")
    @Test
    void buildDynamoDbAsyncTableLocalStack4() {
        DynamoDBConfig dynamoDbConfigLocalStack = new DynamoDBConfig();
        assertNotNull(dynamoDbConfigLocalStack.dynamoDbAsyncTable(
                dynamoDbConfigLocalStack.getDynamoDbEnhancedAsyncClient(
                        dynamoDbConfigLocalStack.amazonDynamoDBAsync()
                )));
    }
}
