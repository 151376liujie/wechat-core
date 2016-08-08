package com.jonnyliu.proj.wechat.message.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by liujie on 2016/8/6 9:42.
 */
public class Image implements Serializable {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String MediaId;

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getMediaId() {
        return MediaId;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
