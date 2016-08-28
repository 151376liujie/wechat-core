package com.jonnyliu.proj.wechat.bean;

import com.jonnyliu.proj.wechat.bean.base.BaseBean;

/**
 * 封装微信tag标签
 * author:980463316@qq.com <br/>
 * Created on 2016-08-28 18:42.
 */
public class WechatTag extends BaseBean {

    private long id;
    private String name;

    public WechatTag() {
    }

    public WechatTag(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
