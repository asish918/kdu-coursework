package com.kdu.smartHome.exceptions.custom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotRegistered extends Exception {
    public UserNotRegistered(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}
