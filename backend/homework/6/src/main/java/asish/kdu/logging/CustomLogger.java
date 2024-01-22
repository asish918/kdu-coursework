package asish.kdu.logging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CustomLogger has been defined with support for various levels of logging based on enum input
 */
public class CustomLogger {
    public enum LoggerType {
        DEBUG,
        INFO,
        WARNING,
        ERROR,
    }

    private static final Logger logger
            = LoggerFactory.getLogger(CustomLogger.class);

    public static void printLogger(String message, LoggerType type) {
        switch (type) {
            case INFO -> logger.info(message);
            case DEBUG -> logger.debug(message);
            case WARNING -> logger.warn(message);
            case ERROR -> logger.error(message);
        }
    }
}
