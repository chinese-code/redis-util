package com.open.source.redis.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * @author tumingjian
 */
public class PropertyUtils {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);
    private static final Map<String, String> DEFAULT_PROPERTIES_FILE_PROPERTY_MAP = PropertyUtils.getProperties("/config.properties");

    /**
     * 读取Properties配置文件内容工具
     *
     * @param propertiesPath
     * @return
     */
    public static Map<String, String> getProperties(String propertiesPath) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            InputStream is = PropertyUtils.class.getResourceAsStream(propertiesPath);
            if (is != null) {
                Properties properties = new Properties();
                BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                properties.load(bf);
                for (Entry<Object, Object> entry : properties.entrySet()) {
                    map.put(String.valueOf(entry.getKey()).trim(), String.valueOf(entry.getValue()).trim());
                }
                is.close();
            } else {
                logger.info("忽略,配置文件不存在:" + propertiesPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String getDefaultPropertiesProperty(String propertyName) {
        if (DEFAULT_PROPERTIES_FILE_PROPERTY_MAP != null) {
            return DEFAULT_PROPERTIES_FILE_PROPERTY_MAP.get(propertyName);
        }
        return null;
    }

    /**
     * 获取一个PropertiesProperty值,如果为空那么返回默认值
     *
     * @param propertyName
     * @param defaultValue
     * @return
     */
    public static String getDefaultPropertiesProperty(String propertyName, String defaultValue) {
        if (DEFAULT_PROPERTIES_FILE_PROPERTY_MAP != null) {
            String value = DEFAULT_PROPERTIES_FILE_PROPERTY_MAP.get(propertyName);
            return value == null ? defaultValue : value;
        }
        return defaultValue;
    }

    /**
     * 获取一个PropertiesProperty值,如果为空那么返回默认值
     *
     * @param propertyName
     * @param defaultValue
     * @return
     */
    public static Integer getDefaultPropertiesProperty(String propertyName, Integer defaultValue) {
        if (DEFAULT_PROPERTIES_FILE_PROPERTY_MAP != null) {
            String value = DEFAULT_PROPERTIES_FILE_PROPERTY_MAP.get(propertyName);
            if (value == null) {
                return defaultValue;
            }
            try {
                Integer integerValue = Integer.valueOf(value);
                return integerValue;
            } catch (Exception e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
}
