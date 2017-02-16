package com.jonnyliu.proj.wechat.bean;

import java.util.List;

/**
 * 封装获取已创建的标签列表
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-29 18:35.
 */
public class GetWechatTagResponse extends BaseBean {

    private List<WechatUserTag> tags;

    public void setTags(List<WechatUserTag> tags) {
        this.tags = tags;
    }

    public List<WechatUserTag> getTags() {
        return tags;
    }
}
