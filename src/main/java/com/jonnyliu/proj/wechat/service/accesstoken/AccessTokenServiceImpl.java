package com.jonnyliu.proj.wechat.service.accesstoken;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonnyliu.proj.wechat.bean.AccessTokenBean;
import com.jonnyliu.proj.wechat.config.WechatConfig;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import com.jonnyliu.proj.wechat.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 微信accessToken的服务实现，所有其他组件都必须调用该组件来获得access token
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-25 14:06.
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenServiceImpl.class);
    private volatile AccessTokenBean ACCESS_TOKEN_BEAN = null;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 全局的获取accesstoken的锁
     */
    private final Object globalFetchTokenLock = new Object();

    @Override
    public AccessTokenBean getAccessToken() {
        if (ACCESS_TOKEN_BEAN == null) {
            synchronized (globalFetchTokenLock) {
                if (ACCESS_TOKEN_BEAN == null) {
                    ACCESS_TOKEN_BEAN = getAccessTokenFromUrl();
                    return ACCESS_TOKEN_BEAN;
                }
            }
        }
        if (isAccessTokenExpired()) {
            ACCESS_TOKEN_BEAN = getAccessTokenFromUrl();
            return ACCESS_TOKEN_BEAN;
        }
        return ACCESS_TOKEN_BEAN;
    }

    @Override
    public void expireAccessToken() {
        synchronized (globalFetchTokenLock) {
            ACCESS_TOKEN_BEAN = null;
        }
    }

    @Override
    public boolean isAccessTokenExpired() {
        synchronized (globalFetchTokenLock) {
            long currentTimeMillis = System.currentTimeMillis();
            long deadTime = ACCESS_TOKEN_BEAN.getDeadTime();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("当前时间:{},token 过期截止日期:{},时间差:{}", new Object[]{currentTimeMillis, deadTime, (deadTime - currentTimeMillis) / 1000.0});
            }
            return deadTime <= currentTimeMillis;
        }
    }

    /**
     * 请求微信服务器获取access token
     *
     * @return
     */
    private AccessTokenBean getAccessTokenFromUrl() {
        String access_token_url = WechatConstant.ACCESS_TOKEN_FETCH_URL.replaceAll("APPID",WechatConfig.getAppId()).replaceAll("APPSECRET",WechatConfig.getAppSercet());
        String content = null;
        try {
            content = HttpClientUtils.sendGet(access_token_url);
            AccessTokenBean accessTokenBean = MAPPER.readValue(content, AccessTokenBean.class);
            return accessTokenBean;
        } catch (IOException e) {
            LOGGER.error("http connection error :" + e.getMessage(),e);
        } catch (Exception e) {
            LOGGER.error("failed to fetch access token. detail error msg :" + content, e);
        }
        return null;
    }

    @Override
    public AccessTokenBean refreshAccessToken() {
        synchronized (globalFetchTokenLock) {
            expireAccessToken();
            return getAccessToken();
        }
    }
}
