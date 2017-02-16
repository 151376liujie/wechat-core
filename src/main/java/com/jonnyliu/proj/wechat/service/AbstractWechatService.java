package com.jonnyliu.proj.wechat.service;

import com.google.common.base.Preconditions;
import com.jonnyliu.proj.wechat.bean.AccessTokenBean;
import com.jonnyliu.proj.wechat.service.accesstoken.AccessTokenService;

/**
<<<<<<< HEAD
 * Created by lenovo on 2017/2/16.
=======
 * 所有微信接口都应该继承该类来获得AccessTokenService对象及检验accessToken是否有效的方法
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2017-02-16 16:26.
>>>>>>> http-code-formate
 */
public abstract class AbstractWechatService{

    protected abstract AccessTokenService getAccessTokenService();

    /**
     * 检查accesstoken是否有效
     */
    protected AccessTokenBean checkAccessToken(){
        AccessTokenBean accessToken = getAccessTokenService().getAccessToken();
        Preconditions.checkNotNull(accessToken,"access token is not allowed to be empty!");
        Preconditions.checkState(!getAccessTokenService().isAccessTokenExpired(),"access token has already expired!");
        return accessToken;
    }
}
