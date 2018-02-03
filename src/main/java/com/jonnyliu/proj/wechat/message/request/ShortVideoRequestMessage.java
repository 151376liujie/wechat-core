package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;
import lombok.Data;

/**
 * 短视频消息的封装
 * Created by jonnyliu-ds8 on 2016/8/5.
 */
@Data
public class ShortVideoRequestMessage extends CommonRequestMessage {

    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;

    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String ThumbMediaId;

    @Override
    public String getMsgType() {
        return MessageType.SHORT_VIDEO_MESSAGE.getTypeStr();
    }

}
