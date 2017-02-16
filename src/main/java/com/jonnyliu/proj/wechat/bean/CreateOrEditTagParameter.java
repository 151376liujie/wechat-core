package com.jonnyliu.proj.wechat.bean;

/**
 * 封装创建标签请求参数
 * author:980463316@qq.com <br/>
 * Created on 2016-08-28 18:54.
 */
public class CreateOrEditTagParameter extends BaseBean {

    private WechatUserTag tag;

    public CreateOrEditTagParameter(WechatUserTag tag) {
        this.tag = tag;
    }

    public void setTag(WechatUserTag tag) {
        this.tag = tag;
    }

    public WechatUserTag getTag() {
        return tag;
    }
}
