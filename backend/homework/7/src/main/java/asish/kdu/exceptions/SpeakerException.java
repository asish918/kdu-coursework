package asish.kdu.exceptions;

import asish.kdu.logging.CustomLogger;

/**
 * Exception class for anything related to Speaker
 */
public class SpeakerException extends Exception {
    public SpeakerException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        CustomLogger.printLogger(err.toString(), CustomLogger.LoggerType.ERROR);
    }
}
