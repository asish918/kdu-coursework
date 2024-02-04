package com.kdu.smartHome.exceptions.custom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryEmptyException extends Exception{
    public QueryEmptyException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}
