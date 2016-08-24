package com.jonnyliu.proj.wechat.utils;

import com.jonnyliu.proj.wechat.bean.AccessTokenBean;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 微信的工具类
 * author:980463316@qq.com <br/>
 * Created on 2016-08-24 23:02.
 */
public class WechatUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatUtils.class);

    public static AccessTokenBean getAccessToken() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String access_token_url = WechatConstant.ACCESS_TOKEN_FETCH_URL + "?grant_type=client_credential&appid="
        HttpGet get = new HttpGet(access_token_url);

        try {
            CloseableHttpResponse httpResponse = httpClient.execute(get);
            httpResponse.getEntity().getContent();
            //TODO
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
