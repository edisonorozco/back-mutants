package co.com.mercadolibre.dynamodb;

import co.com.mercadolibre.dynamodb.helper.EntitySpecieMapper;
import co.com.mercadolibre.dynamodb.repository.SpecieRepository;
import co.com.mercadolibre.model.specie.Specie;
import co.com.mercadolibre.model.specie.gateways.RepositoryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DynamoDbRepositoryImpl implements RepositoryGateway {

    private final SpecieRepository specieRepository;
    private final EntitySpecieMapper entitySpecieMapper;

    @Override
    public Mono<Specie> saveSpecie(Specie specie) {
        return specieRepository.save(entitySpecieMapper.toEntity(specie))
                .map(entitySpecieMapper::toModel);
    }

    @Override
    public Flux<Specie> getSpecies() {
        return specieRepository.findAll()
                .flatMapMany(Flux::fromIterable)
                .map(entitySpecieMapper::toModel);
    }
}
