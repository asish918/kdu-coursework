package com.kdu.smarthome.exceptions.custom;

import lombok.extern.slf4j.Slf4j;

/**
 * Exception for non-admin operations
 */
@Slf4j
public class NonAdminException extends Exception{
    public NonAdminException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}
