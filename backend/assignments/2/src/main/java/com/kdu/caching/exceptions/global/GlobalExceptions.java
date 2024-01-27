package com.kdu.caching.exceptions.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kdu.caching.exceptions.InvalidAddressException;
import com.kdu.caching.exceptions.InvalidParamsException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptions {
    @ExceptionHandler({ InvalidParamsException.class })
    public ResponseEntity<String> paramsException(InvalidParamsException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>("Invalid Request Params", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ InvalidAddressException.class })
    public ResponseEntity<String> addressException(InvalidAddressException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>("Invalid Address", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<String> globalException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>("Some error occured", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
