package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Settings {
    public final String baseUrl = null;
    public final String validUser = null;
    public final String validPassword = null;
    public final String invalidUser = null;
    public final String invalidPassword = null;

    public Settings() {
        this("/settings.properties");
    }

    public Settings(String settingsFile) {
        Logger logger = LoggerFactory.getLogger(Settings.class);
        final Properties properties = new Properties();

        String mode = getMode();

        try (final InputStream stream =
                     this.getClass().getResourceAsStream(settingsFile)) {
            assert(stream != null);
            properties.load(stream);
        } catch (IOException e) {
            logger.error("Could not load settings.properties", e);
        }

        for(Field field: this.getClass().getFields()) {
            if (field.isSynthetic()) {
                continue;
            }
            String fieldName = field.getName();
            String propertyKey = fieldNameToPropertyKey(fieldName);
            logger.debug("Loading '" + fieldName + "' from '" + propertyKey);
            String val = properties.getProperty(propertyKey, null);
            val = properties.getProperty(mode + "." + propertyKey, val);
            if (val == null) {
                logger.warn("Setting " + fieldName + "(" + propertyKey + ") is null");
            }
            try {
                field.setAccessible(true);
                field.set(this, val);
            } catch (IllegalAccessException e) {
                logger.error("Can't make accessible " + propertyKey, e);
            }
        }

    }

    private String fieldNameToPropertyKey(String input) {
        Pattern p = Pattern.compile("[A-Z]+");
        Matcher m = p.matcher(input);

        StringBuffer stringBuffer = new StringBuffer();
        while(m.find()) {
            m.appendReplacement(stringBuffer, "." + m.group().toLowerCase());
        }
        m.appendTail(stringBuffer);

        return stringBuffer.toString();
    }

    private String getMode() {
        String mode = System.getenv("RUN_MODE");
        if (mode == null) {
            mode = "dev";
        }
        mode = System.getProperty("mode", mode);
        return mode;
    }
}
