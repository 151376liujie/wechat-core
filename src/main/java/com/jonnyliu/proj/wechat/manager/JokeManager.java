package com.jonnyliu.proj.wechat.manager;

/**
 * 笑话的redis缓存管理器
 * Author: jonny
 * Time: 2017-02-12 16:23.
 */
public interface JokeManager {

    /**
     * 根据微信用户的openid号获得一个该用户没有浏览过的笑话
     * @param wechatUserOpenId
     * @return
     */
    String getOneJoke(String wechatUserOpenId);

    void clearViewHistory(String wechatUserOpenId);

    void addViewHistory(String wechatUserOpenId, String jokeId);

}
