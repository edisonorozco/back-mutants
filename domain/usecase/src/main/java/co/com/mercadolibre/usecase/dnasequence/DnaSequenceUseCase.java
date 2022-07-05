package co.com.mercadolibre.usecase.dnasequence;

import co.com.mercadolibre.model.dnasequence.response.DnaSequenceResponseUC;
import co.com.mercadolibre.model.dnasequence.resquest.DnaSequence;
import co.com.mercadolibre.model.specie.Specie;
import co.com.mercadolibre.model.specie.gateways.RepositoryGateway;
import co.com.mercadolibre.model.specie.response.StatsUC;
import co.com.mercadolibre.usecase.common.ISpecie;
import co.com.mercadolibre.usecase.util.DnaSequenceUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DnaSequenceUseCase {

    private final RepositoryGateway repositoryGateway;
    private final DnaSequenceUtil dnaSequenceUtil = new DnaSequenceUtil();

    public Mono<DnaSequenceResponseUC> isMutant(DnaSequence dnaSequence) {

        if (!dnaSequenceUtil.isValidDnaSequence(dnaSequence.getDna()))
            return Mono.just(DnaSequenceResponseUC.builder()
                    .mutant(false)
                    .dna("")
                    .build());

        return Mono.just(DnaSequenceResponseUC.builder()
                        .mutant(dnaSequenceUtil.validateDNA(dnaSequence.getDna()))
                        .dna(dnaSequenceUtil.arrayToString(dnaSequence.getDna()))
                        .build())
                .doOnNext(value -> repositoryGateway.saveSpecie(Specie.builder()
                                .dna(value.getDna())
                                .species(value.isMutant() ? ISpecie.MUTANT : ISpecie.HUMAN)
                                .build())
                        .subscribe());
    }

    public Mono<StatsUC> getAllSpecies() {
        return repositoryGateway.getSpecies()
                .collectList()
                .map(species -> {
                    Integer[] stats = dnaSequenceUtil.countMutantDna(species);
                    return StatsUC.builder()
                            .countMutantDna(stats[0])
                            .countHumanDna(stats[1])
                            .ratio(stats[1] > 0 ? (float)stats[0] / stats[1] : stats[0])
                            .build();
                });
    }

}
