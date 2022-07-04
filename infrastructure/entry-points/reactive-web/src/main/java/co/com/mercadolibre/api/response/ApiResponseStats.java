package co.com.mercadolibre.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseStats implements Serializable {
    @JsonProperty("count_mutant_dna")
    private Integer countMutantDna;
    @JsonProperty("count_human_dna")
    private Integer countHumanDna;
    @JsonProperty("ratio")
    private float ratio;
}
