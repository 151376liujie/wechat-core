package com.jonnyliu.proj.wechat.constant;

/**
 * 放置微信中的一些常量
 * author:980463316@qq.com <br/>
 * Created on 2016-08-24 22:59.
 */
public class WechatConstant {
    /**
     * 获取access_token的url
     */
    public static final String ACCESS_TOKEN_FETCH_URL = "https://api.weixin.qq.com/cgi-bin/token";

    /**
     * 获取用户基本信息的url
     */
    public static final String WECHAT_USER_FETCH_URL = "https://api.weixin.qq.com/cgi-bin/user/info";

    /**
     * 批量获取用户基本信息的url
     */
    public static final String WECHAT_USER_BATCH_FETCH_URL = "https://api.weixin.qq.com/cgi-bin/user/info/batchget";

    /**
     * 创建标签url
     */
    public static final String WECHAT_CREATE_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/create";

    /**
     * 获取已创建的标签url
     */
    public static final String WECHAT_GET_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/get";

    /**
     * 编辑标签url
     */
    public static final String WECHAT_EDIT_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/update";
    /**
     * accesstoken的过期时间
     */
    public static final long ACCESS_TOKEN_EXPIRED_TIME = 7200L;

    /**
     * 获取accesstoken的间隔时间
     */
    public static final long ACCESS_TOKEN_FETCH_DELAY = 7000L;

    public static final String DEFAULT_CHARSET = "UTF-8";

}
