package com.jonnyliu.proj.wechat.utils;

import com.google.common.base.Preconditions;
import com.jonnyliu.proj.wechat.bean.NameAndValuePair;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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
import java.util.ArrayList;
import java.util.List;
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
    private static final String URL_QUERYPARAM_SEPARATOR = "?";

    private static CloseableHttpClient httpClient = HttpClients.createDefault();


    public static String sendGet(String url, List<NameAndValuePair<String, String>> nameAndValuePairs) throws Exception {
        return sendGet(url, nameAndValuePairs, null, Charset.forName(WechatConstant.DEFAULT_CHARSET));
    }

    public static String sendPost(String url, String jsonParam) throws Exception {
        return sendPost(url, jsonParam, null, Charset.forName(WechatConstant.DEFAULT_CHARSET));
    }

    private static String sendPost(String url, String jsonParam, Map<String, String> headers, final Charset charset) throws IOException {
        if (StringUtils.isEmpty(url)) {
            LOGGER.error("URL can not be empty or null.");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Post Request:{}", url);
        }
        HttpPost post = new HttpPost(url);

        buildPostParam(post, jsonParam, charset);
        post.setHeaders(buildHeaders(headers));
        String responseText = httpClient.execute(post, new AbstractResponseHandler<String>() {
            @Override
            public String handleEntity(HttpEntity httpEntity) throws IOException {
                return EntityUtils.toString(httpEntity, charset);
            }
        });
        return responseText;
    }

    /**
     * 构建Http Header
     *
     * @param headers
     * @return
     */
    private static Header[] buildHeaders(Map<String, String> headers) {
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
     * @param nameAndValuePairs  GET请求参数
     * @param headers 请求头部参数
     * @param charset 编码
     * @return
     */
    public static String sendGet(String url, List<NameAndValuePair<String, String>> nameAndValuePairs, Map<String, String> headers, final Charset charset) throws Exception {
        if (StringUtils.isEmpty(url)) {
            LOGGER.error("URL can not be empty or null.");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get Request:{}", url);
        }

        String paramStr = buildGetParam(nameAndValuePairs, charset);
        HttpGet request;
        if (paramStr == null) {
            request = new HttpGet(url);
        } else {
            request = new HttpGet(url + URL_QUERYPARAM_SEPARATOR + paramStr);
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

    private static void buildPostParam(HttpPost postReq, String postJsonParam, Charset charset) {
        if (StringUtils.isEmpty(postJsonParam)) {
            return;
        }
        StringEntity entity = new StringEntity(postJsonParam, charset);
        entity.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        entity.setContentEncoding(charset.displayName());
        postReq.setEntity(entity);
    }


    /**
     * 构建get参数
     *
     * @param nameAndValuePairs
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String buildGetParam(List<NameAndValuePair<String, String>> nameAndValuePairs, Charset charset) throws UnsupportedEncodingException {
        if (nameAndValuePairs == null || nameAndValuePairs.isEmpty()) {
            return null;
        }
        StringBuilder strbui = new StringBuilder();
        String charsetName = charset.name();
        for (NameAndValuePair<String, String> nameAndValuePair : nameAndValuePairs) {
            final String encodedName = URLEncoder.encode(nameAndValuePair.getKey(), charsetName);
            final String encodedValue = URLEncoder.encode(nameAndValuePair.getValue(), charsetName);
            strbui.append(encodedName);
            strbui.append(NAME_VALUE_SEPARATOR);
            strbui.append(encodedValue);
            strbui.append(QUERYPARAM_SEP);
        }
        return strbui.substring(0, strbui.length() - 1);
    }

    /**
     * 构建get请求的url（含参数）
     *
     * @param url
     * @param nameAndValuePairs
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String buildUrlWithParam(String url, List<NameAndValuePair<String, String>> nameAndValuePairs, String charset) throws UnsupportedEncodingException {
        return url + URL_QUERYPARAM_SEPARATOR + buildGetParam(nameAndValuePairs, Charset.forName(charset));
    }

    /**
     * 构建带accesstoken的url
     *
     * @param url
     * @param token
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String buildUrlWithToken(String url, String token) throws UnsupportedEncodingException {
        Preconditions.checkNotNull(url, "invalid parameter url : empty");
        Preconditions.checkArgument(StringUtils.isNotEmpty(token), "parameter token is not allowed to be null or empty !");
        List<NameAndValuePair<String, String>> nameAndValuePairs = new ArrayList<>();
        nameAndValuePairs.add(new NameAndValuePair<>("access_token", token));
        return buildUrlWithParam(url, nameAndValuePairs, WechatConstant.DEFAULT_CHARSET);
    }

}
