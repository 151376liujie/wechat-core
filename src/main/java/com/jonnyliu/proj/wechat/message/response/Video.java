package com.jonnyliu.proj.wechat.message.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * 单条视频消息的封装
 * Created by liujie on 2016/8/6 0:09.
 */
@Data
public class Video implements Serializable {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    @XStreamAlias("MediaId")
    private String mediaId;

    /**
     * 视频消息的标题
     */
    @XStreamAlias("Title")
    private String title;

    /**
     * 视频消息的描述
     */
    @XStreamAlias("Description")
    private String description;

}
