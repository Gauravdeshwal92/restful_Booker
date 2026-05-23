package com.framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private final static Properties prop = new Properties();

    static {
        // Read the "env" variable from the system/command line.
        // If it's null (not provided), default it to "dev"
        String env = System.getProperty("env");
        if (env == null || env.isEmpty()) {
            env = "dev";
        }

        // Dynamically build the path to your file (assuming files live in src/test/resources/config/)
        String filename = System.getProperty("user.dir") + "/src/test/resources/config/" + env + ".properties";

        try (FileInputStream fis = new FileInputStream(filename)) {
            prop.load(fis);
            System.out.println("Config successfully loaded for environment: [" + env.toUpperCase() + "]");
        } catch (IOException e) {
            throw new RuntimeException("Fatal Error: Could not load configuration file at: " + filename, e);
        }
    }

    // 4. Exposed public method to read values from memory
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
