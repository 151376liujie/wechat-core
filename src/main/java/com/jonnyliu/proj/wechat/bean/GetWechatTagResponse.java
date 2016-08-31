package com.jonnyliu.proj.wechat.bean;

import java.util.List;

/**
 * 封装获取已创建的标签列表
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-29 18:35.
 */
public class GetWechatTagResponse extends BaseBean {

    private List<WechatTag> tags;

    public void setTags(List<WechatTag> tags) {
        this.tags = tags;
    }

    public List<WechatTag> getTags() {
        return tags;
    }
}
