package asish.kdu.exceptions;

import asish.kdu.logging.CustomLogger;

public class PatronException extends Exception {
    public PatronException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        CustomLogger.printLogger(err.toString(), CustomLogger.LoggerType.ERROR);
    }
}
