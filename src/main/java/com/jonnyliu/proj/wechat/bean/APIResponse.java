package com.jonnyliu.proj.wechat.bean;

import com.jonnyliu.proj.wechat.bean.base.BaseBean;

/**
 * 封装API请求结果
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-29 19:10.
 */
public class APIResponse extends BaseBean {

    private int errcode;
    private String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
