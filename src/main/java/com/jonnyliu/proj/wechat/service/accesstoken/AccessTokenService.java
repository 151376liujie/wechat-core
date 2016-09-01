package com.jonnyliu.proj.wechat.service.accesstoken;

import com.jonnyliu.proj.wechat.bean.AccessTokenBean;

/**
 * 全局唯一的获取access_token的服务，
 * 所有其他组件必须调用该服务获取access_token，否则会造成冲突
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-25 10:51.
 */
public interface AccessTokenService {

    /**
     * 提供获取access_token的方法
     *
     * @return
     */
    AccessTokenBean getAccessToken();

    /**
     * 使当前access token 过期
     */
    void expireAccessToken();

    /**
     * 判断当前access token是否过期；true表示已过期；false表示未过期
     *
     * @return
     */
    boolean isAccessTokenExpired();

    /**
     * 手工刷新access_token
     *
     * @return
     */
    AccessTokenBean refreshAccessToken();


}
