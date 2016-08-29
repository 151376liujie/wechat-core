package com.jonnyliu.proj.wechat.service.user;

import com.jonnyliu.proj.wechat.bean.*;

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
    WechatUser getWechatUserInfo(GetUserInfoParameter userInfoParam);

    /**
     * 批量获取微信用户基本信息
     *
     * @param getUserParamList
     * @return
     */
    List<WechatUser> batchGetWechatUserInfo(BatchGetUserRequestParameter getUserParamList);

    /**
     * 创建tag标签
     *
     * @param tagName
     * @return
     */
    CreateTagResponse createTag(String tagName);

    /**
     * 获取公众号已创建的标签
     *
     * @return
     */
    GetWechatTagResponse getTags();

    /**
     * 编辑标签
     *
     * @param parameter
     * @return
     */
    APIResponse editTag(CreateOrEditTagParameter parameter);

}
