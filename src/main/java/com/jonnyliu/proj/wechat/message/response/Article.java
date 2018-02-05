package com.jonnyliu.proj.wechat.message.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * 单条图文消息
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("item")
public class Article implements Serializable {

    /**
     * 图文消息标题
     */
    @XStreamAlias("Title")
    private String title;

    /**
     * 图文消息描述
     */
    @XStreamAlias("Description")
    private String description;

    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    @XStreamAlias("PicUrl")
    private String picUrl;

    /**
     * 点击图文消息跳转链接
     */
    @XStreamAlias("Url")
    private String url;

}
