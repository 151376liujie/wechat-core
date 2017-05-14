package com.jonnyliu.proj.wechat.config;

import java.io.Serializable;

/**
 * 微信的配置项
 * author:980463316@qq.com <br/>
 * Created on 2016-08-25 20:36.
 */
public class WechatConfig implements Serializable {

    private String appId ;

    private String appsecret;

    private String token;
    
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

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }
}
