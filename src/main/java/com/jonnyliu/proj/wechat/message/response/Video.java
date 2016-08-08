package com.jonnyliu.proj.wechat.message.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 单条视频消息的封装
 * Created by liujie on 2016/8/6 0:09.
 */
public class Video implements Serializable {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String MediaId;

    /**
     * 视频消息的标题
     */
    private String Title;

    /**
     * 视频消息的描述
     */
    private String Description;

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getMediaId() {
        return MediaId;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
