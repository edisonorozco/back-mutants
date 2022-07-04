package co.com.mercadolibre.api.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ExceptionHandlerAdviceTest {
    private final ExceptionHandlerAdvice handler = new ExceptionHandlerAdvice();
    @DisplayName("JUnit test for forbidden exception handle specie not mutant")
    @Test
    void handleExceptionWhenTextNotFound() {
        SpecieAccessForbiddenException specieAccessForbiddenException = new SpecieAccessForbiddenException("Not text found exception");
        ResponseEntity<Object> objectResponseEntity = handler.handleException(specieAccessForbiddenException);
        assertEquals(HttpStatus.FORBIDDEN, objectResponseEntity.getStatusCode());
    }
}
