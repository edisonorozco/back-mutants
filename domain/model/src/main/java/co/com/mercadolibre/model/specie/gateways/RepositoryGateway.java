package co.com.mercadolibre.model.specie.gateways;

import co.com.mercadolibre.model.specie.Specie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RepositoryGateway {
    Mono<Specie> saveSpecie(Specie specie);

    Flux<Specie> getSpecies();
}
