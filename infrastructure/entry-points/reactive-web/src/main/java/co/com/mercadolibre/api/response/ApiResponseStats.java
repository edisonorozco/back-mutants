package co.com.mercadolibre.api.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseStats implements Serializable {
    private Integer count_mutant_dna;
    private Integer count_human_dna;
    private float ratio;
}
