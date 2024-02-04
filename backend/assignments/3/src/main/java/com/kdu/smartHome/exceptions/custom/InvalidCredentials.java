package com.kdu.smartHome.exceptions.custom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidCredentials extends Exception{
    public InvalidCredentials(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}
