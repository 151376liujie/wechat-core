package com.jonnyliu.proj.wechat.bean;

/**
 * 封装微信tag标签
 * author:980463316@qq.com <br/>
 * Created on 2016-08-28 18:42.
 */
public class WechatUserTag extends BaseBean {

    private long id;
    private String name;
    /**
     * 此标签下粉丝数
     */
    private long count;

    public WechatUserTag() {
    }

    public WechatUserTag(long id, String name) {
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

    public void setCount(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }
}
