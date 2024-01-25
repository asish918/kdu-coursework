package asish.kdu.spring_exercise_4.exceptions;

import asish.kdu.spring_exercise_4.logging.CustomLogger;

public class AuthorException extends Exception {
    public AuthorException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        CustomLogger.printLogger(err.toString(), CustomLogger.LoggerType.ERROR);
        CustomLogger.printLogger(err.getCause().toString(), CustomLogger.LoggerType.ERROR);
    }
}
