package Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.class);

    public static void logInfo(String message) {
        logger.info(message);
    }
}
