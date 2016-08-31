package com.jonnyliu.proj.wechat.bean;

import java.util.List;

/**
 * 封装批量获取微信用户基本信息的请求参数
 * author:980463316@qq.com <br/>
 * Created on 2016-08-28 1:05.
 */
public class BatchGetUserRequestParameter extends BaseBean {

    private List<GetUserInfoParameter> user_list;

    public List<GetUserInfoParameter> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<GetUserInfoParameter> user_list) {
        this.user_list = user_list;
    }
}
