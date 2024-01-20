package asish.kdu.exceptions;

import asish.kdu.logging.CustomLogger;

public class QueryException extends Exception {
    public QueryException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        CustomLogger.printLogger(err.toString(), CustomLogger.LoggerType.ERROR);
    }
}
