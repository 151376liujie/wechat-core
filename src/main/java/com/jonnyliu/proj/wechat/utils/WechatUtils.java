package com.jonnyliu.proj.wechat.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonnyliu.proj.wechat.bean.AccessTokenBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * 微信的工具类
 * author:980463316@qq.com <br/>
 * Created on 2016-08-24 23:02.
 */
public class WechatUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatUtils.class);
    private static AccessTokenBean ACCESS_TOKEN_BEAN = null;
    private static final ObjectMapper MAPPER = new ObjectMapper();

//    private static final object


//    private static AccessTokenBean getAccessTokenFromUrl(String appId,String appsecret) {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
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
//    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String content = "<p>Last month, production began on Warner Bros.' and Alcon's long-awaited <a href=\\\"http:\\/\\/movieweb.com\\/movie\\/blade-runner-2\\\">Blade Runner 2<\\/a>, which brings back the iconic Harrison Ford as <a href=\\\"http:\\/\\/movieweb.com\\/blade-runner-2-harrison-ford-deckard-replicant\\\">Rick Deckard<\\/a>, along with a slew of new cast members. We have word that tragedy has struck the set in Budapest, Hungary, when one of the sets collapsed and killed a construction worker. None of the cast and crew members were present at the time of this accident, which took place earlier today.<\\/p><p><a href=\\\"http:\\/\\/movieweb.com\\/blade-runner-2-set-collapses-kills-construction-worker\\\">Read more...<\\/a><\\/p>\"";
        Document document = Jsoup.parse(content);
        Elements elements = document.getElementsByTag("a");
        Element last = elements.last();
        String href = last.attr("href");
        LOGGER.info(last.tagName() + "===>" + href.replace("\\", "").replace("\"", ""));
    }

}
