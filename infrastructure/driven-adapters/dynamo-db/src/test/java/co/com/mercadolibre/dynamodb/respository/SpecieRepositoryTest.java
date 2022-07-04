package co.com.mercadolibre.dynamodb.respository;

import co.com.mercadolibre.dynamodb.entity.Specie;
import co.com.mercadolibre.dynamodb.repository.SpecieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SpecieRepositoryTest {

    private static final String DNA_ONE = "dna_1";

    @InjectMocks
    private SpecieRepository specieRepository;

    @Mock
    private DynamoDbAsyncTable<Specie> specieDynamoDbAsyncTable;
    private CompletableFuture<Specie> completableFuture;
    private Specie specieEntity;

    @BeforeEach
    void setUp() {
        specieRepository = new SpecieRepository(specieDynamoDbAsyncTable);
        specieEntity = buildSpecieObject();
        completableFuture = CompletableFuture.completedFuture(specieEntity);
    }

    @DisplayName("JUnit test for update method from Repository")
    @Test
    void saveTest() {
        when(specieDynamoDbAsyncTable.updateItem(any(Specie.class))).thenReturn(completableFuture);
        specieEntity.setDna(DNA_ONE);
        Mono<Specie> specieUpdateMono = specieRepository.save(specieEntity);
        verify(specieDynamoDbAsyncTable, times(1)).updateItem(any(Specie.class));
        assertEquals(DNA_ONE, Objects.requireNonNull(specieUpdateMono.block()).getDna(), "Unexpected value");
        StepVerifier.create(specieUpdateMono)
                .expectNext(specieEntity)
                .expectComplete()
                .verify();
    }

    @DisplayName("JUnit test for findAll method from Repository")
    @Test
    void whenFindAllIsTriggeredThenNoException() {
        when(specieDynamoDbAsyncTable.scan()).thenReturn(s -> Mono.just(
                Collections.singletonList(specieEntity)));
        specieRepository.findAll();
        verify(specieDynamoDbAsyncTable, times(1)).scan();
    }

    private Specie buildSpecieObject() {
        return Specie.builder()
                .dna("dna")
                .species("species")
                .build();
    }

}
