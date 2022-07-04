package co.com.mercadolibre.api.exception;

import co.com.mercadolibre.api.util.ApiDateUtil;
import co.com.mercadolibre.model.common.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    public static final String TEXT_ACCESS_DENIED_EXCEPTION = "Access denied exception";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        final HttpStatus status = HttpStatus.FORBIDDEN;
        return buildResponseEntity(ApiError.builder()
                        .message(e.getMessage())
                        .code(String.valueOf(status.value()))
                        .timestamp(ApiDateUtil.formatLocalDateTime(LocalDateTime.now()))
                        .error(TEXT_ACCESS_DENIED_EXCEPTION)
                        .build(),
                status);
    }

    private ResponseEntity<Object> buildResponseEntity(final ApiError apiError, final HttpStatus httpStatus) {
        return new ResponseEntity<>(apiError, httpStatus);
    }

}
