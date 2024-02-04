package com.kdu.smartHome.exceptions.custom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NonAdminException extends Exception{
    public NonAdminException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}
