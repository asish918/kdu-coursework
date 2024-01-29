package com.example.springbootapidemo.exception.custom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}
