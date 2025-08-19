package Utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Lightweight configuration reader.
 * Loads properties from classpath file 'config.properties'
 * and lets JVM System properties (-Dkey=value) override them.
 */
public final class ConfigReader {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                PROPS.load(in);
            } else {
                LoggerUtils.logWarn("config.properties not found on classpath; defaults/system properties will be used.");
            }
        } catch (Exception e) {
            LoggerUtils.logWarn("Failed to load config.properties: " + e.getMessage());
        }
    }

    private ConfigReader() {}

    /** Get a property with a default, allowing -D overrides. */
    public static String get(String key, String defaultValue) {
        return System.getProperty(key, PROPS.getProperty(key, defaultValue));
    }

    /** Shortcut for the base URL used by tests. */
    public static String getBaseUrl() {
        return get("base.url", "https://www.saucedemo.com/");
    }
}
