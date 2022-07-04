package co.com.mercadolibre.dynamodb.service;

import co.com.mercadolibre.dynamodb.helper.EntitySpecieMapper;
import co.com.mercadolibre.dynamodb.repository.SpecieRepository;
import co.com.mercadolibre.model.specie.Specie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DynamoDbRepositoryImplTest {

    @Mock
    private SpecieRepository specieRepository;

    @Mock
    private EntitySpecieMapper entitySpecieMapper;

    @Autowired
    @InjectMocks
    private DynamoDbRepositoryImpl specieService;

    private static final String DNA_SEQUENCE = "dna_sequence";
    private co.com.mercadolibre.dynamodb.entity.Specie specieEntity;
    private Specie specieModel;

    @BeforeEach
    void setUp() {
        specieService = new DynamoDbRepositoryImpl(specieRepository,entitySpecieMapper);
        specieEntity = buildSpecieObject();
        specieModel = Specie.builder()
                .dna("dna_sequence_2")
                .species("")
                .build();
    }

    @DisplayName("JUnit test for saveSpecie method")
    @Test
    void whenUpdateIsTriggeredThenNoException() {
        when(specieRepository.save(any())).thenReturn(Mono.just(specieEntity));
        specieEntity.setDna(DNA_SEQUENCE);
        specieEntity.setSpecies("");
        specieService.saveSpecie(specieModel);
        verify(specieRepository, times(1)).save(any());
        assertEquals(DNA_SEQUENCE, specieEntity.getDna(), "Unexpected value");
    }

    @DisplayName("JUnit test for getAll method")
    @Test
    void whenGetAllIsTriggeredThenNoException() {
        when(entitySpecieMapper.toModel(any())).thenReturn(specieModel);
        Mono<List<co.com.mercadolibre.dynamodb.entity.Specie>> list = Mono.just(Collections.singletonList(specieEntity));
        when(specieRepository.findAll()).thenReturn(list);
        Flux<Specie> specieServiceList = specieService.getSpecies();
        verify(specieRepository, times(1)).findAll();
        StepVerifier.create(specieServiceList)
                .expectNext(specieModel)
                .expectComplete()
                .verify();
    }

    private co.com.mercadolibre.dynamodb.entity.Specie buildSpecieObject() {
        return co.com.mercadolibre.dynamodb.entity.Specie.builder()
                .dna("dna")
                .species("species")
                .build();
    }
}
