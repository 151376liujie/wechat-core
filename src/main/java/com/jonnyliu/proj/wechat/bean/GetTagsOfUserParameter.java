package com.jonnyliu.proj.wechat.bean;

/**
 * 封装获取用户所属的标签请求参数
 * author:980463316@qq.com <br/>
 * Created on 2016-08-31 21:28.
 */
public class GetTagsOfUserParameter extends BaseBean {

    private String openid;

    public GetTagsOfUserParameter(String openId) {
        this.openid = openId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
