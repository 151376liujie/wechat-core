package com.jonnyliu.proj.wechat.bean;

/**
 * 封装获取标签下所有用户列表请求参数
 * author:980463316@qq.com <br/>
 * Created on 2016-08-29 21:15.
 */
public class GetUsersOfTagParameter extends BaseBean {

    private long tagId;
    private String next_openid;

    public GetUsersOfTagParameter(long tagId, String next_openid) {
        this.tagId = tagId;
        this.next_openid = next_openid;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }
}
