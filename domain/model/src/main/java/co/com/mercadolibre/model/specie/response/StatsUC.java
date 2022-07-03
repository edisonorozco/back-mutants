package co.com.mercadolibre.model.specie.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StatsUC implements Serializable {
    private Integer countMutantDna;
    private Integer countHumanDna;
    private float ratio;
}
