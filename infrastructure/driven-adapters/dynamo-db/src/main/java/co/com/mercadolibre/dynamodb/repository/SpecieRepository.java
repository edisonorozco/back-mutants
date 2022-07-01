package co.com.mercadolibre.dynamodb.repository;

import co.com.mercadolibre.dynamodb.entity.Specie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpecieRepository {

    private final DynamoDbAsyncTable<Specie> specieDynamoDbAsyncTable;

    public Mono<Specie> save(Specie specie) {
        return Mono.fromFuture(
                specieDynamoDbAsyncTable.updateItem(specie)
        );
    }

    public Mono<List<Specie>> findAll() {
        return Mono.from(specieDynamoDbAsyncTable.scan()).map(page -> new ArrayList<>(page.items()));
    }

}
