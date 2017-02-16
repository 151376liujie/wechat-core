package com.jonnyliu.proj.wechat.service;

import com.google.common.base.Preconditions;
import com.jonnyliu.proj.wechat.bean.AccessTokenBean;
import com.jonnyliu.proj.wechat.service.accesstoken.AccessTokenService;

/**
 * Created by lenovo on 2017/2/16.
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
