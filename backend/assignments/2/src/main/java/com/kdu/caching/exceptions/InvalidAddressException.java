package com.kdu.caching.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * Custom Exception thrown in case of Invalid Address
 */
@Slf4j
public class InvalidAddressException extends Exception {
    public InvalidAddressException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}
