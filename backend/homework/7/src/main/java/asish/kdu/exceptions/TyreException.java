package asish.kdu.exceptions;

import asish.kdu.logging.CustomLogger;

/**
 * Exception class for anything related to Tyres
 */
public class TyreException  extends Exception {
    public TyreException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        CustomLogger.printLogger(err.toString(), CustomLogger.LoggerType.ERROR);
    }
}
