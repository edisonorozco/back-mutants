package co.com.mercadolibre.model.specie;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Specie implements Serializable {
    private static final long serialVersionUID = 3311365355100285450L;
    private String dna;
    private String species;
}
