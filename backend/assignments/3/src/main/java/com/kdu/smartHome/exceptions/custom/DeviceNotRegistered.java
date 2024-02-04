package com.kdu.smartHome.exceptions.custom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeviceNotRegistered extends Exception {
    public DeviceNotRegistered(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}
