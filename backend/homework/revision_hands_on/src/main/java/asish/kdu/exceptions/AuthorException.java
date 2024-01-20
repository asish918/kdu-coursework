package asish.kdu.exceptions;

import asish.kdu.logging.CustomLogger;

public class AuthorException extends Exception{
    public AuthorException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        CustomLogger.printLogger(err.toString(), CustomLogger.LoggerType.ERROR);
    }
}
