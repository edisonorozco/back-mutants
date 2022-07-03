package co.com.mercadolibre.api.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseStats implements Serializable {
    private Integer countMutantDna;
    private Integer countHumanDna;
    private float ratio;
}
