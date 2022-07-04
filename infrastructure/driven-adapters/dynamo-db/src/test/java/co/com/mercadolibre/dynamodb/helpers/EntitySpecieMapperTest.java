package co.com.mercadolibre.dynamodb.helpers;

import co.com.mercadolibre.dynamodb.helper.EntitySpecieMapper;
import co.com.mercadolibre.model.specie.Specie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EntitySpecieMapperTest {

    EntitySpecieMapper entitySpecieMapper;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapperImp();
        entitySpecieMapper = new EntitySpecieMapper(objectMapper);
    }

    @Test
    void toEntity() {
        Specie specie = Specie.builder().dna("dna").species("species").build();
        co.com.mercadolibre.dynamodb.entity.Specie entity = entitySpecieMapper.toEntity(specie);
        assertEquals(specie.getDna(), entity.getDna());
    }

    @Test
    void toModel() {
        co.com.mercadolibre.dynamodb.entity.Specie entity = co.com.mercadolibre.dynamodb.entity.Specie.builder()
                .dna("dna")
                .species("species")
                .build();

        Specie specie = entitySpecieMapper.toModel(entity);
        assertEquals(entity.getDna(), specie.getDna());
    }

    @Test
    void toModelWithNullEntity() {
        Specie specie = entitySpecieMapper.toModel(null);
        assertNull((specie));
    }

}
