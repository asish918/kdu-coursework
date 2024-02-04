package com.kdu.smarthome.utils;

import com.kdu.smarthome.exceptions.custom.InvalidParamsException;

/**
 * Utility function to validate Request Parameters by checking if we are getting values
 * that can be converted to Long
 */
public class ParamsValidator {
    public static void requestParamValidator(String id) throws InvalidParamsException {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidParamsException("Invalid Parameters", e);
        }
    }
}
