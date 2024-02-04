package com.kdu.smarthome.exceptions.custom;

import lombok.extern.slf4j.Slf4j;

/**
 * Exception for Invalid Credentials of a particular device
 */
@Slf4j
public class InvalidCredentials extends Exception{
    public InvalidCredentials(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}
