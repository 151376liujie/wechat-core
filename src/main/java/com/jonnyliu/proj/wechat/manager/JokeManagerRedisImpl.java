package com.jonnyliu.proj.wechat.manager;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

/**
 * Author: jonny
 * Time: 2017-02-12 16:29.
 */
@Component
public class JokeManagerRedisImpl implements JokeManager {

    //微信用户的笑话浏览记录key
    private static final String S_KEY_JOKE_VIEW_HISTORY = "joke_view_history_%s";

    //总的笑话id列表key
    private static final String S_KEY_JOKE_IDS = "joke_ids";

    //单个笑话key
    public static final String KEY_JOKE = "joke_%s";

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 根据微信用户的openid号获得一个该用户没有浏览过的笑话
     *
     * @param wechatUserOpenId
     * @return
     */
    @Override
    public String getOneJoke(String wechatUserOpenId) {
        String key_joke_view_history = String.format(S_KEY_JOKE_VIEW_HISTORY,wechatUserOpenId);

        //把总的joke_id列表和微信用户的笑话浏览记录进行差集运算，得出用户还未看过的joke_id列表
        Set<String> joke_ids = redisTemplate.opsForSet().difference(S_KEY_JOKE_IDS, key_joke_view_history);

        if (joke_ids == null || joke_ids.isEmpty()){
            return "好尴尬啊，笑话不够用了！哈哈哈哈....";
        }

        //随机从joke列表中选中一个joke_id
        String joke_id = this.randomOf(joke_ids);
        //根据joke_id再从joke列表中拿到笑话内容
        String joke = redisTemplate.opsForValue().get(String.format( KEY_JOKE, joke_id));

        //添加微信用户的笑话浏览记录，表示该微信用户已经看过该笑话
        this.addViewHistory(wechatUserOpenId,joke_id);
        return joke;
    }

    /**
     * 清空指定微信用户的笑话浏览记录
     * @param wechatUserOpenId
     */
    @Override
    public void clearViewHistory(String wechatUserOpenId){
        String key_joke_view_history = String.format(S_KEY_JOKE_VIEW_HISTORY,wechatUserOpenId);
        redisTemplate.delete(key_joke_view_history);
    }

    /**
     * 将指定笑话加入到指定微信用户的笑话浏览记录中
     * @param wechatUserOpenId
     * @param jokeId
     */
    @Override
    public void addViewHistory(String wechatUserOpenId, String jokeId){
        String key_joke_view_history = String.format(S_KEY_JOKE_VIEW_HISTORY,wechatUserOpenId);
        redisTemplate.opsForSet().add(key_joke_view_history,jokeId);
    }

    private <T extends Object> T randomOf(Collection<T> collection){
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        int index = RandomUtils.nextInt(0, collection.size());
        T joke_id = (T) collection.toArray()[index];
        return joke_id;
    }
}
