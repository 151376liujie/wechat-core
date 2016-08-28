package com.jonnyliu.proj.wechat.bean;

import com.jonnyliu.proj.wechat.bean.base.BaseBean;

/**
 * 封装创建标签请求参数
 * author:980463316@qq.com <br/>
 * Created on 2016-08-28 18:54.
 */
public class CreateTagParameter extends BaseBean {
    private WechatTag tag;

    public CreateTagParameter(WechatTag tag) {
        this.tag = tag;
    }

    public void setTag(WechatTag tag) {
        this.tag = tag;
    }

    public WechatTag getTag() {
        return tag;
    }
}
