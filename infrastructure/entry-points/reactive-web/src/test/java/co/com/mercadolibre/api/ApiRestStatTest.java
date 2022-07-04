package co.com.mercadolibre.api;

import co.com.mercadolibre.model.specie.response.StatsUC;
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

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApiRestStat.class})
@WebFluxTest
@TestPropertySource(locations = "classpath:api-route.properties")
public class ApiRestStatTest {

    @Value("${path.prefix}")
    String prefix;

    @Value("${path.retrieve.stats}")
    String pathStats;

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
    void getAllSpeciesTest() {

        when(dnaSequenceUseCase.getAllSpecies()).thenReturn(Mono.just(buildResponseStats()));

        webTestClient.get()
                .uri(prefix.concat(pathStats))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody();
    }

    private StatsUC buildResponseStats() {
        return StatsUC.builder()
                .countHumanDna(1)
                .countMutantDna(1)
                .ratio(1.0F)
                .build();
    }

}
