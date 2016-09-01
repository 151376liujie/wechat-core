package com.jonnyliu.proj.wechat.bean;

/**
 * 封装获取指定用户的标签
 * author:980463316@qq.com <br/>
 * Created on 2016-08-31 21:09.
 */
public class GetTagsOfUserResponse extends BaseBean {

    private long[] tagid_list;

    public long[] getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(long[] tagid_list) {
        this.tagid_list = tagid_list;
    }
}
