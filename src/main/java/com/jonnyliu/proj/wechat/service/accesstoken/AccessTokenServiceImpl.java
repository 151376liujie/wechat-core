package com.jonnyliu.proj.wechat.service.accesstoken;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonnyliu.proj.wechat.bean.AccessTokenBean;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 微信accessToken的服务实现，所有其他组件都必须调用该组件来获得access token
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-25 14:06.
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Value("#{wechatProperty.appID}")
    private String appId;

    @Value("#{wechatProperty.appsecret}")
    private String appsecret;

    private AccessTokenBean ACCESS_TOKEN_BEAN = null;

    private ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenServiceImpl.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    {
        LOGGER.info("accesstoken scheduledExecutor starting...");
        scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                ACCESS_TOKEN_BEAN = getAccessTokenFromUrl();
                LOGGER.info("time:{},fetched token :{}", System.currentTimeMillis(), ACCESS_TOKEN_BEAN);
            }
        }, 0, WechatConstant.ACCESS_TOKEN_FETCH_DELAY, TimeUnit.SECONDS);
    }

    @Override
    public AccessTokenBean getAccessToken() {
        return ACCESS_TOKEN_BEAN;
    }

    @Override
    public AccessTokenBean refreshAccessToken() {
        ACCESS_TOKEN_BEAN = getAccessTokenFromUrl();
        return ACCESS_TOKEN_BEAN;
    }

    /**
     * 请求微信服务器获取access token
     *
     * @return
     */
    private AccessTokenBean getAccessTokenFromUrl() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String access_token_url = WechatConstant.ACCESS_TOKEN_FETCH_URL + "?grant_type=client_credential&appid=" + appId + "&secret=" + appsecret;
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
            LOGGER.error("failed to fetch access token. detail error msg :{}", content);
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
}
