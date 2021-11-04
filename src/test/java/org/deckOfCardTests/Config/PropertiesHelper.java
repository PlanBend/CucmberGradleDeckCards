package org.deckOfCardTests.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {
    static final String PROPERTIES_PATH = "src/test/resources/deckApiTests.properties";

    public static Properties getProperties() {
        Properties prop = new Properties();
        try (
                InputStream input = new FileInputStream(PROPERTIES_PATH)) {
            prop.load(input);
            return prop;

        } catch (
                IOException io) {
            io.printStackTrace();
            return prop;
        }
    }
}
