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
    public static final String ACCESS_TOKEN_FETCH_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取用户基本信息的url
     */
    public static final String WECHAT_USER_FETCH_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=LANG";

    /**
     * 批量获取用户基本信息的url
     */
    public static final String WECHAT_USER_BATCH_FETCH_URL = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";

    /**
     * 创建标签url
     */
    public static final String WECHAT_CREATE_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";

    /**
     * 获取已创建的标签url
     */
    public static final String WECHAT_GET_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";

    /**
     * 编辑标签url
     */
    public static final String WECHAT_EDIT_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";

    /**
     * 删除标签url
     */
    public static final String WECHAT_DELETE_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN";

    /**
     * 获取该标签下的用户列表url
     */
    public static final String WECHAT_GET_USER_OF_TAG_URL = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN";

    /**
     * 批量为用户打标签url
     */
    public static final String WECHAT_BATCH_TAG_USER_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";

    /**
     * 批量为用户取消标签url
     */
    public static final String WECHAT_BATCH_UNTAG_USER_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN";

    /**
     * 获取用户身上标注的标签列表url
     */
    public static final String WECHAT_GET_TAGS_OF_USER_URL = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=ACCESS_TOKEN";


    /**
     * 给用户备注的url
     */
    public static final String WECHAT_REMARK_USER_URL = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";

    /**
     * 获取公众号的黑名单列表
     */
    public static final String WECHAT_GET_BLACK_LIST_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=ACCESS_TOKEN";

    /**
     * 拉黑用户url
     */
    public static final String WECHAT_BLACK_USER_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token=ACCESS_TOKEN";

    /**
     * 取消拉黑用户url
     */
    public static final String WECHAT_UNBLACK_USER_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=ACCESS_TOKEN";

    /**
     * accesstoken的过期时间
     */
    public static final long ACCESS_TOKEN_EXPIRED_TIME = 7200L;

    /**
     * accesstoken过期提前量（提前200秒过期）
     */
    public static final long ACCESS_TOKEN_EXPIRED_delta = 200L;

    /**
     * 默认编码
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 最多一次拉黑人数
     */
    public static final int WECHAT_BLACK_USER_MAX_SIZE = 20;

    /**
     * 给用户备注的最大长度
     */
    public static final int WECHAT_USER_REMARK_MAX_LENGTH = 30;
    /**
     * 用户标签的最大长度
     */
    public static final int WECHAT_USER_TAG_LENGTH = 30;
    /**
     * url中的accessToken占位符
     */
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
}
