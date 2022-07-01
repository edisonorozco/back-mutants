package co.com.mercadolibre.usecase.dnasequence;

import co.com.mercadolibre.model.dnasequence.response.DnaSequenceResponseUC;
import co.com.mercadolibre.model.dnasequence.resquest.DnaSequence;
import co.com.mercadolibre.model.specie.Specie;
import co.com.mercadolibre.model.specie.gateways.RepositoryGateway;
import co.com.mercadolibre.model.specie.response.StatsUC;
import co.com.mercadolibre.usecase.util.DnaSequenceUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DnaSequenceUseCase {

    private final RepositoryGateway repositoryGateway;
    private final DnaSequenceUtil dnaSequenceUtil = new DnaSequenceUtil();

    public Mono<DnaSequenceResponseUC> isMutant(DnaSequence dnaSequence) {
        return Mono.just(DnaSequenceResponseUC.builder()
                .mutant(dnaSequenceUtil.validateDNA(dnaSequence.getDna()))
                .dna(dnaSequenceUtil.arrayToString(dnaSequence.getDna()))
                .build())
                .doOnNext(value -> repositoryGateway.saveSpecie(Specie.builder()
                        .dna(value.getDna())
                        .species(value.isMutant() ? "mutant" : "human")
                        .build())
                        .subscribe());
    }

    public Mono<StatsUC> getAllSpecies() {
        return Mono.empty()
                .flatMapMany(listSpecies -> repositoryGateway.getSpecies())
                .collectList()
                .map(specieList -> {
                    Integer[] stats = dnaSequenceUtil.countMutantDna(specieList);
                    return StatsUC.builder()
                            .count_mutant_dna(stats[0])
                            .count_human_dna(stats[1])
                            .ratio((float) stats[0] / stats[1])
                            .build();
                });
    }

}
