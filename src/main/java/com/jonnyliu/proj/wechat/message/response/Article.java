package com.jonnyliu.proj.wechat.message.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 单条图文消息
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class Article implements Serializable {

    /**
     * 图文消息标题
     */
    private String Title;

    /**
     * 图文消息描述
     */
    private String Description;

    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    private String PicUrl;

    /**
     * 点击图文消息跳转链接
     */
    private String Url;

}
