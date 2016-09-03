package com.jonnyliu.proj.wechat.config;

import com.jonnyliu.proj.wechat.utils.PropertiesReader;

import java.io.Serializable;

/**
 * 微信的配置项
 * author:980463316@qq.com <br/>
 * Created on 2016-08-25 20:36.
 */
public class WechatConfig implements Serializable {

    private static final String APPID = "appId";
    private static final String APPSERCET = "appsecret";
    private static final String TOKEN = "token";
    private static final String ENCODINGAESKEY = "encodingAESKey";

    public static String getAppId() {
        return PropertiesReader.getString(APPID);
    }


    public static String getAppSercet() {
        return PropertiesReader.getString(APPSERCET);
    }

    public static String getToken() {
        return PropertiesReader.getString(TOKEN);
    }

    public static String getEncodingAESKey() {
        return PropertiesReader.getString(ENCODINGAESKEY);
    }

}
