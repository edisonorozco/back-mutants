package co.com.mercadolibre.dynamodb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean()
public class Specie implements Serializable {

    private String dna;
    private String species;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("dna")
    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    @DynamoDbAttribute("species")
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
