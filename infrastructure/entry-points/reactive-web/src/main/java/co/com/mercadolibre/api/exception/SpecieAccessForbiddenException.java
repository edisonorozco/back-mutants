package co.com.mercadolibre.api.exception;

public class SpecieAccessForbiddenException extends RuntimeException{
    public SpecieAccessForbiddenException(String message) {
        super(message);
    }
}
