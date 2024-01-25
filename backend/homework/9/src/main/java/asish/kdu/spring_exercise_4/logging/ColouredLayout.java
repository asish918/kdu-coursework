package asish.kdu.spring_exercise_4.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;

/**
 * This is a custom layout file for Logback.
 * Currently, I am not using it, but I just
 * made it try my hands on and probably use
 * this template sometime in the future.
 * It decides color based on Different levels of logging
 */
public class ColouredLayout extends LayoutBase<ILoggingEvent> {

    public String doLayout(ILoggingEvent event) {
        StringBuilder sbuf = new StringBuilder(128);

        switch (event.getLevel().toInt()) {
            case Level.ERROR_INT:
                sbuf.append("\u001B[31m"); // Red for ERROR
                break;
            case Level.WARN_INT:
                sbuf.append("\u001B[33m"); // Yellow for WARN
                break;
            case Level.INFO_INT:
                sbuf.append("\u001B[32m"); // Green for INFO
                break;
            case Level.DEBUG_INT:
                sbuf.append("\u001B[34m"); // Blue for DEBUG
                break;
            case Level.TRACE_INT:
                sbuf.append("\u001B[35m"); // Magenta for TRACE
                break;
            default:
                break;
        }

        sbuf.append(event.getTimeStamp());
        sbuf.append(" ");
        sbuf.append(event.getLevel());
        sbuf.append(" [");
        sbuf.append(event.getThreadName());
        sbuf.append("] ");
        sbuf.append(event.getLoggerName());
        sbuf.append(" - ");
        sbuf.append(event.getFormattedMessage());

        sbuf.append("\u001B[0m");

        sbuf.append("\n");

        return sbuf.toString();
    }
}