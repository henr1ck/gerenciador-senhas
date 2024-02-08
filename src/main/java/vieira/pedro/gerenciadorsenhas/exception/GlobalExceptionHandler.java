package vieira.pedro.gerenciadorsenhas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception){
        ErrorDetails error = new ErrorDetails();
        error.setTitle("Resource not found!");
        error.setDetails(exception.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(error.getStatus())
                .body(error);
    }
}
