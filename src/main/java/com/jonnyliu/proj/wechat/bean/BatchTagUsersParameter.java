package com.jonnyliu.proj.wechat.bean;

/**
 * 封装批量尾用户打标签的请求参数
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-30 11:12.
 */
public class BatchTagUsersParameter extends BaseBean {

    private long tagid;

    private String[] openid_list;

    public BatchTagUsersParameter(long tagid, String[] openid_list) {
        this.tagid = tagid;
        this.openid_list = openid_list;
    }

    public long getTagid() {
        return tagid;
    }

    public void setTagid(long tagid) {
        this.tagid = tagid;
    }

    public String[] getOpenid_list() {
        return openid_list;
    }

    public void setOpenid_list(String[] openid_list) {
        this.openid_list = openid_list;
    }
}
