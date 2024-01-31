package asish.kdu.sring_jdbc_homework.exceptions;

import asish.kdu.sring_jdbc_homework.exceptions.custom.NoSQLDataFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptions {
    @ExceptionHandler({NoSQLDataFound.class})
    public ResponseEntity<String> noData(NoSQLDataFound e){
        log.error("No data found for the query.");
        return new ResponseEntity<>("No data found for the query.", HttpStatus.OK);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> globalException(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
    }
}
