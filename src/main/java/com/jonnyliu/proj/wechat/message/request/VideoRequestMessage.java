package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 视频消息的封装
 * Created by jonnyliu-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("xml")
public class VideoRequestMessage extends CommonRequestMessage {

    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @XStreamAlias("MediaId")
    private String mediaId;

    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;

    @Override
    public String getMsgType() {
        return MessageType.VIDEO_MESSAGE.getTypeStr();
    }

}
