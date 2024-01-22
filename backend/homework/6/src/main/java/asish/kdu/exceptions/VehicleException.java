package asish.kdu.exceptions;

import asish.kdu.logging.CustomLogger;

/**
 * Exception class for anything related to Vehicles
 */
public class VehicleException extends Exception {
    public VehicleException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        CustomLogger.printLogger(err.toString(), CustomLogger.LoggerType.ERROR);
    }
}
