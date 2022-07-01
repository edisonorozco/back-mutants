package co.com.mercadolibre.model.specie.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StatsUC implements Serializable {
    private Integer count_mutant_dna;
    private Integer count_human_dna;
    private float ratio;
}
