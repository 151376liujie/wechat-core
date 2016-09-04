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
     * 删除标签url
     */
    public static final String WECHAT_DELETE_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/delete";

    /**
     * 获取该标签下的用户列表url
     */
    public static final String WECHAT_GET_USER_OF_TAG_URL = "https://api.weixin.qq.com/cgi-bin/user/tag/get";

    /**
     * 批量为用户打标签url
     */
    public static final String WECHAT_BATCH_TAG_USER_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging";

    /**
     * 批量为用户取消标签url
     */
    public static final String WECHAT_BATCH_UNTAG_USER_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging";

    /**
     * 获取用户身上标注的标签列表url
     */
    public static final String WECHAT_GET_TAGS_OF_USER_URL = "https://api.weixin.qq.com/cgi-bin/tags/getidlist";


    /**
     * 给用户备注的url
     */
    public static final String WECHAT_REMARK_USER_URL = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark";

    /**
     * 获取公众号的黑名单列表
     */
    public static final String WECHAT_GET_BLACK_LIST_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist";

    /**
     * 拉黑用户url
     */
    public static final String WECHAT_BLACK_USER_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist";

    /**
     * 取消拉黑用户url
     */
    public static final String WECHAT_UNBLACK_USER_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist";

    /**
     * accesstoken的过期时间
     */
    public static final long ACCESS_TOKEN_EXPIRED_TIME = 7200L;

    /**
     * 获取accesstoken的间隔时间
     */
    public static final long ACCESS_TOKEN_FETCH_DELAY = 7000L;

    /**
     * 默认编码
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 最多一次拉黑人数
     */
    public static final int WECHAT_BLACK_USER_MAX_SIZE = 20;

    /**
     * 给用户备注的最大程度
     */
    public static final int WECHAT_USER_REMARK_MAX_LENGTH = 30;

}
