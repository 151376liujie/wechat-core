package com.jonnyliu.proj.wechat.service.user;

import com.jonnyliu.proj.wechat.bean.WechatUser;
import com.jonnyliu.proj.wechat.enums.Lang;

/**
 * 微信用户相关服务
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-25 16:52.
 */
public interface WechatUserService {

    /**
     * 获取指定用户的基本信息
     *
     * @param openid
     * @param lang
     * @return
     */
    public WechatUser getWechatUserInfo(String openid, Lang lang);


}
