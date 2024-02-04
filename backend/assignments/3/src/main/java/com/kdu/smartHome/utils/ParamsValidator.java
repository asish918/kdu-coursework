package com.kdu.smartHome.utils;

import com.kdu.smartHome.exceptions.custom.InvalidParamsException;

public class ParamsValidator {
    public static void requestParamValidator(String id) throws InvalidParamsException {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidParamsException("Invalid Parameters", e);
        }
    }
}
