package com.kdu.caching.utils;

import com.kdu.caching.exceptions.InvalidParamsException;

/**
 * A Validator class implemented using the Singleton pattern
 * that contains a function to validate the Request Params
 * of the controller
 */
public class Validator {
    private Validator() {

    }

    public static void validateParams(String latitude, String longitude) throws InvalidParamsException {
        try {
            Double.parseDouble(latitude);
            Double.parseDouble(longitude);
        } catch (NumberFormatException e) {
            throw new InvalidParamsException("Invalid Parameters", e);
        }
    }
}
