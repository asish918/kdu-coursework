package asish.kdu.spring_exercise_4.exceptions.global;

import asish.kdu.spring_exercise_4.exceptions.AuthorException;
import asish.kdu.spring_exercise_4.exceptions.BookException;
import asish.kdu.spring_exercise_4.exceptions.PatronException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import asish.kdu.spring_exercise_4.logging.CustomLogger;
import asish.kdu.spring_exercise_4.logging.CustomLogger.LoggerType;

@ControllerAdvice
public class GlobalExceptions extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ BookException.class })
    public ResponseEntity<String> bookException(BookException e) {
        CustomLogger.printLogger("Some Error Occured in Book Class", LoggerType.ERROR);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ AuthorException.class })
    public ResponseEntity<String> authorException(AuthorException e) {
        CustomLogger.printLogger("Some Error Occured in Author Class", LoggerType.ERROR);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ PatronException.class })
    public ResponseEntity<String> patronException(PatronException e) {
        CustomLogger.printLogger("Some Error Occured in Patron Class", LoggerType.ERROR);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<String> globalException(Exception e) {
        CustomLogger.printLogger(e.getMessage(), LoggerType.ERROR);
        CustomLogger.printLogger(e.getCause().toString(), LoggerType.ERROR);
        return new ResponseEntity<>("Some Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}