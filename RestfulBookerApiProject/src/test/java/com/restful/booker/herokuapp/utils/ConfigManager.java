package com.restful.booker.herokuapp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Config file could not be loaded!", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);


    }
}
