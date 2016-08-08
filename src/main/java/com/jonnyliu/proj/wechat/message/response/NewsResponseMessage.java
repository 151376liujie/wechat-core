package com.jonnyliu.proj.wechat.message.response;

import java.util.List;

/**
 * 图文消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
public class NewsResponseMessage extends BaseResponseMessage {

    /**
     * 图文消息个数，限制为10条以内
     */
    private int ArticleCount;

    /**
     * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
     */
    private List<Article> Articles;


    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}
