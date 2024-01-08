package asish.kdu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger
{
    private static final Logger logger
            = LoggerFactory.getLogger(CustomLogger.class);

    public static void printLogger(String message)
    {
        logger.debug(message);
    }
}
