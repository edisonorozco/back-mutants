package co.com.mercadolibre.api.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDnaSequence implements Serializable {
    private boolean mutant;
}
