package com.zxftech.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyUtils.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
