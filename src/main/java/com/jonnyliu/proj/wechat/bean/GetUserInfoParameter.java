package com.jonnyliu.proj.wechat.bean;

/**
 * 单个获取微信用户的请求参数封装
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-26 17:12.
 */
public class GetUserInfoParameter extends BaseBean {

    private String openid;
    private String lang;

    public GetUserInfoParameter(String openid, String lang) {
        this.openid = openid;
        this.lang = lang;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


}
