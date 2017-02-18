package com.jonnyliu.proj.wechat.config;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * 微信的配置项
 * author:980463316@qq.com <br/>
 * Created on 2016-08-25 20:36.
 */
public class WechatConfig implements Serializable {

    @Value("#{wechatProperties['appId']}")
    private String appId = "appId";

    @Value("#{wechatProperties['appsecret']}")
    private String appsecret;

    @Value("#{wechatProperties['token']}")
    private String token;
    
    @Value("#{wechatProperties['encodingAESKey']}")
    private String encodingAESKey;

    public String getAppId() {
        return appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public String getToken() {
        return token;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }
}
