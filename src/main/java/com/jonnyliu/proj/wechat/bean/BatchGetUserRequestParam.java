package com.jonnyliu.proj.wechat.bean;

import com.jonnyliu.proj.wechat.bean.base.BaseBean;

import java.util.List;

/**
 * author:980463316@qq.com <br/>
 * Created on 2016-08-28 1:05.
 */
public class BatchGetUserRequestParam extends BaseBean {

    private List<GetUserInfoParam> user_list;

    public List<GetUserInfoParam> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<GetUserInfoParam> user_list) {
        this.user_list = user_list;
    }
}
