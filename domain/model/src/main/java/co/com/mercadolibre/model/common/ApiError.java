package co.com.mercadolibre.model.common;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private String code;
    private String error;
    private String message;
    private String timestamp;
}
