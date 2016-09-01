package com.jonnyliu.proj.wechat.bean;

/**
 * 封装请求得到的access_token
 * author:980463316@qq.com <br/>
 * Created on 2016-08-24 23:04.
 */
public class AccessTokenBean extends BaseBean {

    private String access_token;
    /**
     * 凭证有效期，单位：秒
     */
    private long expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

}
