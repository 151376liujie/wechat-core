package com.jonnyliu.proj.wechat.bean;

/**
 * author:980463316@qq.com <br/>
 * Created on 2016-08-28 18:36.
 */
public class CreateWechatUserTagResponse extends BaseBean {

    private WechatUserTag tag;

    public void setTag(WechatUserTag tag) {
        this.tag = tag;
    }

    public WechatUserTag getTag() {
        return tag;
    }
}
