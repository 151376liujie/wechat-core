package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;

/**
 * 图片消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
public class ImageRequestMessage extends BaseRequestMessage {

    /**
     * 图片链接（由系统生成）
     */
    private String PicUrl;

    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    @Override
    public String getMsgType() {
        return MessageType.IMAGE_MESSAGE.getTypeStr();
    }
}
