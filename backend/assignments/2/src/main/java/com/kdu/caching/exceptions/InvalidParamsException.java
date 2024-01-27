package com.kdu.caching.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidParamsException extends Exception {
    public InvalidParamsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}
