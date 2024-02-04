package com.kdu.smarthome.exceptions.custom;

import lombok.extern.slf4j.Slf4j;

/**
 * Exception for Invalid Request Params
 */
@Slf4j
public class InvalidParamsException extends Exception {
    public InvalidParamsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}