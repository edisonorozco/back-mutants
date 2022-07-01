package co.com.mercadolibre.config;

import co.com.mercadolibre.model.specie.gateways.RepositoryGateway;
import co.com.mercadolibre.usecase.dnasequence.DnaSequenceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Autowired
    RepositoryGateway repositoryGateway;

    @Bean
    public DnaSequenceUseCase buildDmaSequenceUseCase() {
        return new DnaSequenceUseCase(repositoryGateway);
    }

}
