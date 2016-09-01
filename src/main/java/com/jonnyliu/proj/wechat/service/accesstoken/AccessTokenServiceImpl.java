package com.jonnyliu.proj.wechat.service.accesstoken;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonnyliu.proj.wechat.bean.AccessTokenBean;
import com.jonnyliu.proj.wechat.bean.WechatConfig;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
    private static volatile AccessTokenBean ACCESS_TOKEN_BEAN = new AccessTokenBean();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 全局的获取accesstoken的锁
     */
    private final Object globalFetchTokenLock = new Object();

    @Override
    public AccessTokenBean getAccessToken() {
        if (isAccessTokenExpired()) {
            //token过期，需要重新获取
            synchronized (globalFetchTokenLock) {
                ACCESS_TOKEN_BEAN = getAccessTokenFromUrl();
            }
        }
        return ACCESS_TOKEN_BEAN;
    }

    @Override
    public void expireAccessToken() {
        synchronized (globalFetchTokenLock) {
            ACCESS_TOKEN_BEAN.setExpires_in(0);
        }
    }

    @Override
    public boolean isAccessTokenExpired() {
        synchronized (globalFetchTokenLock) {
            return ACCESS_TOKEN_BEAN.getExpires_in() == 0;
        }
    }

    public static void main(String[] args) {
        AccessTokenServiceImpl accessTokenService = new AccessTokenServiceImpl();
        AccessTokenBean accessToken = accessTokenService.getAccessToken();
        System.out.println(accessToken.getAccess_token() + "==" + accessTokenService.isAccessTokenExpired());

        System.out.println("===================================");

        accessToken = accessTokenService.getAccessToken();
        System.out.println(accessToken.getAccess_token() + "==" + accessTokenService.isAccessTokenExpired());

        System.out.println("===================================");

        accessToken = accessTokenService.getAccessToken();
        System.out.println(accessToken.getAccess_token() + "==" + accessTokenService.isAccessTokenExpired());
    }

    /**
     * 请求微信服务器获取access token
     *
     * @return
     */
    private static AccessTokenBean getAccessTokenFromUrl() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String access_token_url = WechatConstant.ACCESS_TOKEN_FETCH_URL + "?grant_type=client_credential&appid=" + WechatConfig.getAppId() + "&secret=" + WechatConfig.getAppSercet();
        HttpGet get = new HttpGet(access_token_url);
        String content = null;
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(get);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                content = EntityUtils.toString(httpResponse.getEntity());
                AccessTokenBean accessTokenBean = MAPPER.readValue(content, AccessTokenBean.class);
                return accessTokenBean;
            }
        } catch (IOException e) {
            LOGGER.error("failed to fetch access token. detail error msg :" + content, e);
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
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
