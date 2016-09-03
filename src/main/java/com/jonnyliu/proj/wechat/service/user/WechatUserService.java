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

    /**
     * 删除指定id的标签
     *
     * @param tagId
     * @return
     */
    APIResponse deleteTag(long tagId);

    /**
     * 获取指定标签下的用户id（微信号）
     *
     * @param tagId       标签id
     * @param next_openid 第一个拉取的OPENID，不填默认从头开始拉取
     * @return
     */
    GetUsersOfTagResponse getUsersOfTag(long tagId, String next_openid);

    /**
     * 批量为指定用户打上指定标签
     *
     * @param tagId
     * @param openid_list
     * @return
     */
    APIResponse batchTagUsers(long tagId, List<String> openid_list);

    /**
     * 批量为指定用户取消标签
     *
     * @param tagId
     * @param openid_list
     * @return
     */
    APIResponse batchUnTagUsers(long tagId, List<String> openid_list);

    /**
     * 获取指定微信用户的标签
     *
     * @param openId
     * @return
     */
    GetTagsOfUserResponse getTagsOfUser(String openId);


    /**
     * 给指定用户加上备注
     *
     * @param openId
     * @param remark
     * @return
     */
    APIResponse remarkUser(String openId, String remark);

    /**
     * 获取公众号的黑名单列表
     * 该接口每次调用最多可拉取 10000 个OpenID，当列表数较多时，
     * 可以通过多次拉取的方式来满足需求。
     *
     * @param begin_openid 当begin_openid为空时，默认从开头拉取。
     * @return
     */
    GetUserBlackListResponse getUserBlackList(String begin_openid);

}
