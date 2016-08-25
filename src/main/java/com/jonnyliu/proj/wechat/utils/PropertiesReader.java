package com.jonnyliu.proj.wechat.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties文件读取工具类
 * author:980463316@qq.com <br/>
 * Created on 2016-08-25 20:41.
 */
public final class PropertiesReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesReader.class);

    private static final String WECHAT_PROPERTIES_FILE = "wechat.properties";

    private static Properties PROPERTIES = null;

    static {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(WECHAT_PROPERTIES_FILE);
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 获取指定key的value
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return PROPERTIES.getProperty(key);
    }

    /**
     * 获取指定key的value，如key不存在，返回default val
     *
     * @param key
     * @param defaultVal
     * @return
     */
    public static String getString(String key, String defaultVal) {
        return PROPERTIES.getProperty(key, defaultVal);
    }

}
