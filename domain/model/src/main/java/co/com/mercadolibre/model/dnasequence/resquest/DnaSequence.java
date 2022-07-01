package co.com.mercadolibre.model.dnasequence.resquest;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DnaSequence implements Serializable {
    private String[] dna;
}
