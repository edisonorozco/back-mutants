package co.com.mercadolibre.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiRestValidationMessage {
    public static final String NOT_NULL = "es requerido";
    public static final String PREFIX_PARAMETER = "El parametro";
}
