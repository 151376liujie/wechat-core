package com.jonnyliu.proj.wechat.bean;

/**
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-09-02 18:57.
 */
public class RemarkUserParameter extends BaseBean {

    private String openid;
    private String remark;

    public RemarkUserParameter(String openid, String remark) {
        this.openid = openid;
        this.remark = remark;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
