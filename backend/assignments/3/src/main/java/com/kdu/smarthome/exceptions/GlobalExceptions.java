package com.kdu.smarthome.exceptions;

import com.kdu.smarthome.exceptions.custom.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Global exception handler for exceptions thrown by the controller layer
 */
@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<String> invalidToken() {
        return new ResponseEntity<>("Invalid/Expired Token", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<String> handleAuthenticationException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed at controller advice");
    }

    @ExceptionHandler({ NonAdminException.class })
    public ResponseEntity<String> nonAdmin(NonAdminException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ UserNotRegistered.class })
    public ResponseEntity<String> notRegistered(UserNotRegistered e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ InvalidParamsException.class })
    public ResponseEntity<String> paramsException(InvalidParamsException e) {
        return new ResponseEntity<>("Invalid Request Params", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ QueryEmptyException.class })
    public ResponseEntity<String> queryException(QueryEmptyException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ InvalidCredentials.class })
    public ResponseEntity<String> credentialsException(InvalidCredentials e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ DeviceNotRegistered.class })
    public ResponseEntity<String> registerException(DeviceNotRegistered e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
