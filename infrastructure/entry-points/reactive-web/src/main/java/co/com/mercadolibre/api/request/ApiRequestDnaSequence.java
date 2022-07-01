package co.com.mercadolibre.api.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static co.com.mercadolibre.api.util.ApiRestValidationMessage.NOT_NULL;
import static co.com.mercadolibre.api.util.ApiRestValidationMessage.PREFIX_PARAMETER;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequestDnaSequence implements Serializable {
    @Valid
    @NotNull(message = PREFIX_PARAMETER + " dna " + NOT_NULL)
    private String[] dna;
}
