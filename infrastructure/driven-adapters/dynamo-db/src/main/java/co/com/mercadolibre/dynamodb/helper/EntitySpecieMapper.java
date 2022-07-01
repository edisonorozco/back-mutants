package co.com.mercadolibre.dynamodb.helper;

import co.com.mercadolibre.model.specie.Specie;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntitySpecieMapper {

    private final ObjectMapper mapper;

    public co.com.mercadolibre.dynamodb.entity.Specie toEntity(Specie specie) {
        return mapper.map(specie, co.com.mercadolibre.dynamodb.entity.Specie.class);
    }

    public Specie toModel(co.com.mercadolibre.dynamodb.entity.Specie specie) {
        return specie != null ? mapper.map(specie, Specie.class) : null;
    }

}
