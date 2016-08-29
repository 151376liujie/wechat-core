package com.jonnyliu.proj.wechat.bean;

import com.jonnyliu.proj.wechat.bean.base.BaseBean;

/**
 * author:980463316@qq.com <br/>
 * Created on 2016-08-29 21:18.
 */
public class FansOfTag extends BaseBean {

    private String[] openid;

    public String[] getOpenid() {
        return openid;
    }

    public void setOpenid(String[] openid) {
        this.openid = openid;
    }
}
