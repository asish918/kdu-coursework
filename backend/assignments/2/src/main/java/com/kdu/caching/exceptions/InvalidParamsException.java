package com.kdu.caching.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * Custom expression thrown in case of Invalid Request Params
 */
@Slf4j
public class InvalidParamsException extends Exception {
    public InvalidParamsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}
