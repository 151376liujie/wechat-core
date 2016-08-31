package com.jonnyliu.proj.wechat.bean;

/**
 * author:980463316@qq.com <br/>
 * Created on 2016-08-28 18:36.
 */
public class CreateTagResponse extends BaseBean {

    private WechatTag tag;

    public void setTag(WechatTag tag) {
        this.tag = tag;
    }

    public WechatTag getTag() {
        return tag;
    }
}
