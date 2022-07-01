package co.com.mercadolibre.model.dnasequence.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DnaSequenceResponseUC implements Serializable {
    private boolean mutant;
    private String dna;
}

