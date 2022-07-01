package co.com.mercadolibre.config;

import co.com.mercadolibre.usecase.dnasequence.DnaSequenceUseCase;
import co.com.mercadolibre.usecase.util.DnaSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Autowired
    DnaSequenceUtil dnaSequenceUtil;

    @Bean
    public DnaSequenceUseCase buildDmaSequenceUseCase() {
        return new DnaSequenceUseCase(dnaSequenceUtil);
    }

}
