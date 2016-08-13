package com.jonnyliu.proj.wechat.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬虫工具类 <br/>
 * author : 980463316@qq.com
 * Created on 2016/8/7 16:38.
 */
public class SpliderUtils {

    private static final String FUN_URL = "http://www.lsw1994.com/api/xh/";

    private static final Logger LOGGER = LoggerFactory.getLogger(SpliderUtils.class);

    /**
     * 抓取网页内容，提取感兴趣的内容
     *
     * @param url
     * @return
     */
    public static List<String> parseUrl(String url) {
        List<String> list = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).timeout(3000).get();
            System.out.println(document);
            TextNode textNode = document.body().textNodes().get(0);
            list.add(textNode.text());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return list;
    }

}
