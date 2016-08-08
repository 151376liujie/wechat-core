package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;

/**
 * (短)视频消息的封装
 * Created by jonnyliu-ds8 on 2016/8/5.
 */
public class VideoRequestMessage extends BaseRequestMessage {

    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;

    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    @Override
    public String getMsgType() {
        return MessageType.VIDEO_MESSAGE.getTypeStr();
    }
}
