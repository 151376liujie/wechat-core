package com.jonnyliu.proj.wechat.message.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author: jonny
 * Time: 2017-08-19 10:16.
 */
@Data
public class SendPicsInfo implements Serializable {
    /**
     * 发送图片的数量
     */
    @XStreamAlias("Count")
    private int count;
    /**
     * 图片列表
     */
    @XStreamAlias("PicList")
    private List<PhotoItem> picList;

}
