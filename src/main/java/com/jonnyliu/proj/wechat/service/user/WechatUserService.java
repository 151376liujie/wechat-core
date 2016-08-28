package com.jonnyliu.proj.wechat.service.user;

import com.jonnyliu.proj.wechat.bean.BatchGetUserRequestParam;
import com.jonnyliu.proj.wechat.bean.GetUserInfoParam;
import com.jonnyliu.proj.wechat.bean.WechatUser;

import java.util.List;

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
     * @return
     */
    public WechatUser getWechatUserInfo(GetUserInfoParam userInfoParam);

    /**
     * 批量获取微信用户基本信息
     *
     * @param getUserParamList
     * @return
     */
    public List<WechatUser> batchGetWechatUserInfo(BatchGetUserRequestParam getUserParamList);

}
