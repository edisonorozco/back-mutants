package co.com.mercadolibre.api;

import co.com.mercadolibre.model.dnasequence.response.DnaSequenceResponseUC;
import co.com.mercadolibre.model.dnasequence.resquest.DnaSequence;
import co.com.mercadolibre.usecase.dnasequence.DnaSequenceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApiRestDnaSequence.class})
@WebFluxTest
@TestPropertySource(locations = "classpath:api-route.properties")
public class ApiRestDnaSequenceTest {

    @Value("${path.prefix}")
    String prefix;

    @Value("${path.retrieve.mutant}")
    String pathDna;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private DnaSequenceUseCase dnaSequenceUseCase;

    @BeforeEach
    public void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void isMutantTest() {
        when(dnaSequenceUseCase.isMutant(any())).thenReturn(Mono.just(buildDnaSequenceResponseUC()));

        webTestClient.post()
                .uri(prefix.concat(pathDna))
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(buildDnaSequence())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody();
    }

    private DnaSequenceResponseUC buildDnaSequenceResponseUC() {
        return DnaSequenceResponseUC.builder()
                .dna("")
                .mutant(true)
                .build();
    }

    private DnaSequence buildDnaSequence() {
        return DnaSequence.builder()
                .dna(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"})
                .build();
    }

}
