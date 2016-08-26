package com.jonnyliu.proj.wechat.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * http请求的工具类
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-25 17:42.
 */
public final class HttpClientUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String QUERYPARAM_SEP = "&";

    private static CloseableHttpClient httpClient = HttpClients.createDefault();


    public void sendGet(String url, Map<String, String> param) {
        String str = "asdfasdfasdf";


//        String access_token_url = WechatConstant.ACCESS_TOKEN_FETCH_URL + "?grant_type=client_credential&appid="+appId+"&secret="+appsecret;
//        HttpGet get = new HttpGet(access_token_url);
//        String content = null;
//        try {
//            CloseableHttpResponse httpResponse = httpClient.execute(get);
//            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
//                content = EntityUtils.toString(httpResponse.getEntity());
//                AccessTokenBean accessTokenBean = MAPPER.readValue(content, AccessTokenBean.class);
//                return accessTokenBean;
//            }
//        } catch (IOException e) {
//            LOGGER.error("failed to fetch access token. detail error msg :{}",content);
//        }finally {
//            try {
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//            } catch (IOException e) {
//                LOGGER.error(e.getMessage(),e);
//            }
//        }
//        return null;
    }

    /**
     * 构建Http Header
     *
     * @param headers
     * @return
     */
    private Header[] buildHeaders(Map<String, String> headers) {
        if (headers != null) {
            Header[] tmp = new BasicHeader[headers.size()];
            int i = 0;
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                tmp[i] = new BasicHeader(entry.getKey(), entry.getValue());
                i++;
            }
            return tmp;
        }
        return null;
    }

    /**
     * 发送get请求
     *
     * @param url     请求的URL
     * @param params  GET请求参数
     * @param headers 请求头部参数
     * @param charset 编码
     * @return
     */
    public String sendGet(String url, Map<String, String> params, Map<String, String> headers, final Charset charset) throws Exception {
        if (StringUtils.isEmpty(url)) {
            LOGGER.error("URL can not be empty or null.");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get Request:{}", url);
        }

        String paramStr = buildGetParam(params, charset);
        HttpGet request;
        if (paramStr == null) {
            request = new HttpGet(url);
        } else {
            request = new HttpGet(url + "?" + paramStr);
        }
        request.setHeaders(buildHeaders(headers));
        String responseText = httpClient.execute(request, new AbstractResponseHandler<String>() {
            @Override
            public String handleEntity(HttpEntity httpEntity) throws IOException {
                return EntityUtils.toString(httpEntity, charset);
            }
        });
        return responseText;
    }

    /**
     * 构建get参数
     *
     * @param params
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    private String buildGetParam(Map<String, String> params, Charset charset) throws UnsupportedEncodingException {
        if (params == null || params.isEmpty()) {
            return null;
        }
        StringBuilder strbui = new StringBuilder();
        String charsetName = charset.name();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            final String encodedName = URLEncoder.encode(entry.getKey(), charsetName);
            final String encodedValue = URLEncoder.encode(entry.getValue(), charsetName);
            if (strbui.length() > 1) {
                strbui.append(QUERYPARAM_SEP);
            }
            strbui.append(encodedName);
            strbui.append(NAME_VALUE_SEPARATOR);
            strbui.append(encodedValue);
        }
        return strbui.toString();
    }


}
