package co.com.mercadolibre.usecase.dnasequence;

import co.com.mercadolibre.model.dnasequence.response.DnaSequenceResponseUC;
import co.com.mercadolibre.model.dnasequence.resquest.DnaSequence;
import co.com.mercadolibre.model.specie.Specie;
import co.com.mercadolibre.model.specie.gateways.RepositoryGateway;
import co.com.mercadolibre.model.specie.response.StatsUC;
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
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DnaSequenceUseCaseTest {

    private static final String[] DNA_1 = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    private static final String[] DNA_2 = {"TAGCGA", "CAGTGC", "TTATGT", "AGAAGG", "ATCCTA", "TCACTG"};
    private static final String[] DNA_3 = {"TAGCGA", "CAGTGC", "RTATGT", "AGAAGG", "ATCCTA", "TCACTG"};

    @Mock
    private RepositoryGateway repositoryGateway;

    @Autowired
    @InjectMocks
    private DnaSequenceUseCase dnaSequenceUseCase;

    @BeforeEach
    void setUp() {
        dnaSequenceUseCase = new DnaSequenceUseCase(repositoryGateway);
    }

    @DisplayName("JUnit test for validate when dna is mutant")
    @Test
    void isMutantTestTrueResponse() {
        when(repositoryGateway.saveSpecie(any(Specie.class))).thenReturn(Mono.just(buildSpecieObject()));

        StepVerifier
                .create(dnaSequenceUseCase.isMutant(DnaSequence.builder().dna(DNA_1).build()))
                .expectNext(DnaSequenceResponseUC.builder().mutant(true).dna(Arrays.toString(DNA_1)).build())
                .expectComplete()
                .verify();
    }

    @DisplayName("JUnit test for validate when dna is human")
    @Test
    void isMutantTestFalseResponse() {
        when(repositoryGateway.saveSpecie(any(Specie.class))).thenReturn(Mono.just(buildSpecieObject()));

        StepVerifier
                .create(dnaSequenceUseCase.isMutant(DnaSequence.builder().dna(DNA_2).build()))
                .expectNext(DnaSequenceResponseUC.builder().mutant(false).dna(Arrays.toString(DNA_2)).build())
                .expectComplete()
                .verify();
    }

    @DisplayName("JUnit test for validate when dna sequence is right")
    @Test
    void isMutantTestInvalidDnaSequence() {
        StepVerifier
                .create(dnaSequenceUseCase.isMutant(DnaSequence.builder().dna(DNA_3).build()))
                .expectNext(DnaSequenceResponseUC.builder().mutant(false).dna("").build())
                .expectComplete()
                .verify();
    }

    @DisplayName("JUnit test for validate stats dna")
    @Test
    void getAllSpeciesTest() {
        when(repositoryGateway.getSpecies()).thenReturn(Flux.fromIterable(buildListSpecies()));

        StepVerifier
                .create(dnaSequenceUseCase.getAllSpecies())
                .expectNext(StatsUC.builder()
                        .countMutantDna(2)
                        .countHumanDna(1)
                        .ratio(2.0F)
                        .build())
                .expectComplete()
                .verify();
    }

    private Specie buildSpecieObject() {
        return Specie.builder()
                .dna("dna")
                .species("species")
                .build();
    }

    private List<Specie> buildListSpecies() {
        Specie specie1 = Specie.builder().dna("dna1").species("human").build();
        Specie specie2 = Specie.builder().dna("dna2").species("mutant").build();
        Specie specie3 = Specie.builder().dna("dna3").species("mutant").build();
        return Arrays.asList(specie1,specie2,specie3);
    }

}
