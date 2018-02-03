package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;
import lombok.Data;

/**
 * 图片消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class ImageRequestMessage extends CommonRequestMessage {


    /**
     * 图片链接（由系统生成）
     */
    private String PicUrl;

    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;

    @Override
    public String getMsgType() {
        return MessageType.IMAGE_MESSAGE.getTypeStr();
    }

}
