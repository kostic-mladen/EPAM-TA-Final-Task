package Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Centralized logging utility wrapping SLF4J.
 * Use this class instead of System.out or ad-hoc loggers.
 */
public class LoggerUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.class);

    /** Informational logs for test steps. */
    public static void logInfo(String message) {
        logger.info(message);
    }

    /** Debug logs for troubleshooting. */
    public static void logDebug(String message) {
        logger.debug(message);
    }

    /** Warnings: unexpected but non-fatal situations. */
    public static void logWarn(String message) {
        logger.warn(message);
    }

    /** Errors: failures and exceptions. */
    public static void logError(String message, Throwable t) {
        logger.error(message, t);
    }
}
