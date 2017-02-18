package com.jonnyliu.proj.wechat.utils;

import com.jonnyliu.proj.wechat.constant.WechatConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
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
    private static final String QUERY_PARAM_SEP = "&";
    private static final String URL_QUERY_PARAM_SEPARATOR = "?";

    private static final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManagerShared(true).build();

    public static String sendGet(String url) throws Exception {
        return sendGet(url, null);
    }

    public static String sendGet(String url, List<NameValuePair> nameAndValuePairs){
        return sendGet(url, nameAndValuePairs, null, Charset.forName(WechatConstant.DEFAULT_CHARSET));
    }

    public static String sendPost(String url, String jsonParam){
        return sendPost(url, jsonParam, null, Charset.forName(WechatConstant.DEFAULT_CHARSET));
    }

    public static String sendPost(String url, String jsonParam, Map<String, String> headers, final Charset charset) {
        if (StringUtils.isEmpty(url)) {
            LOGGER.error("URL can not be empty or null.");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Post Request:{}", url);
        }
        try {
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
        } catch (IOException e) {
            LOGGER.error("http connection error :" + e.getMessage(), e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return null;
    }

    /**
     * 发送get请求
     *
     * @param url               请求的URL
     * @param nameAndValuePairs GET请求参数
     * @param headers           请求头部参数
     * @param charset           编码
     * @return
     */
    public static String sendGet(String url, List<NameValuePair> nameAndValuePairs, Map<String, String> headers, final Charset charset) {
        if (StringUtils.isEmpty(url)) {
            LOGGER.error("URL can not be empty or null.");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get Request:{}", url);
        }
        try {
            String urlWithParams = buildUrlWithParams(url,nameAndValuePairs, charset);
            HttpGet request = new HttpGet(urlWithParams);

            request.setHeaders(buildHeaders(headers));
            String responseText = httpClient.execute(request, new AbstractResponseHandler<String>() {
                @Override
                public String handleEntity(HttpEntity httpEntity) throws IOException {
                    return EntityUtils.toString(httpEntity, charset);
                }
            });
            return responseText;
        } catch (IOException e) {
            LOGGER.error("http connection error :" + e.getMessage(), e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return null;
    }

    /**
     * 构建Http Header
     *
     * @param headers
     * @return
     */
    private static Header[] buildHeaders(Map<String, String> headers) {
        if (headers == null || headers.isEmpty()){
            return null;
        }
        Header[] tmp = new BasicHeader[headers.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            tmp[i] = new BasicHeader(entry.getKey(), entry.getValue());
            i++;
        }
        return tmp;
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
     *
     * @param url
     * @param nameAndValuePairs
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String buildUrlWithParams(String url, List<NameValuePair> nameAndValuePairs, Charset charset) throws UnsupportedEncodingException {
        if (nameAndValuePairs == null || nameAndValuePairs.isEmpty()) {
            return url;
        }
        StringBuilder strbui = new StringBuilder();
        String charsetName = charset.name();
        for (NameValuePair nameAndValuePair : nameAndValuePairs) {
            final String encodedName = URLEncoder.encode(nameAndValuePair.getName(), charsetName);
            final String encodedValue = URLEncoder.encode(nameAndValuePair.getValue(), charsetName);
            strbui.append(encodedName);
            strbui.append(NAME_VALUE_SEPARATOR);
            strbui.append(encodedValue);
            strbui.append(QUERY_PARAM_SEP);
        }
        String paramStr = strbui.substring(0, strbui.length() - 1);
        return url + URL_QUERY_PARAM_SEPARATOR + paramStr;
    }

}
