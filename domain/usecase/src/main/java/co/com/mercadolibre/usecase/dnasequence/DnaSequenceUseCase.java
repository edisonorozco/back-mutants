package co.com.mercadolibre.usecase.dnasequence;

import co.com.mercadolibre.model.dnasequence.response.DnaSequenceResponseUC;
import co.com.mercadolibre.model.dnasequence.resquest.DnaSequence;
import co.com.mercadolibre.usecase.util.DnaSequenceUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DnaSequenceUseCase {

    private final DnaSequenceUtil dnaSequenceUtil;

    public Mono<DnaSequenceResponseUC> isMutant(DnaSequence dnaSequence) {
        return Mono.just(DnaSequenceResponseUC.builder()
                .mutant(dnaSequenceUtil.validateDNA(dnaSequence.getDna()))
                .build());
    }

}
