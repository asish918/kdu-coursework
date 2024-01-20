package asish.kdu.exceptions;

import asish.kdu.logging.CustomLogger;

public class BookException extends Exception {
    public BookException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        CustomLogger.printLogger(err.toString(), CustomLogger.LoggerType.ERROR);
    }
}
