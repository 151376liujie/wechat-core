package com.jonnyliu.proj.wechat.bean;

import com.jonnyliu.proj.wechat.bean.base.BaseBean;

import java.util.List;

/**
 * 封装批量获取微信用户基本信息的返回结果
 * author:980463316@qq.com <br/>
 * Created on 2016-08-28 17:35.
 */
public class BatchGetUserInfoResponse extends BaseBean {

    private List<WechatUser> user_info_list;

    public List<WechatUser> getUser_info_list() {
        return user_info_list;
    }

    public void setUser_info_list(List<WechatUser> user_info_list) {
        this.user_info_list = user_info_list;
    }
}
