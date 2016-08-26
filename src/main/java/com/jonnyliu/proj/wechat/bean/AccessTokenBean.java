package com.jonnyliu.proj.wechat.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 封装请求得到的access_token
 * author:980463316@qq.com <br/>
 * Created on 2016-08-24 23:04.
 */
public class AccessTokenBean implements Serializable {

    private String access_token;
    /**
     * 凭证有效期，单位：秒
     */
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
