package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 图片消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("xml")
public class ImageRequestMessage extends CommonRequestMessage {


    /**
     * 图片链接（由系统生成）
     */
    @XStreamAlias("PicUrl")
    private String picUrl;

    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @XStreamAlias("MediaId")
    private String mediaId;

    @Override
    public String getMsgType() {
        return MessageType.IMAGE_MESSAGE.getTypeStr();
    }

}
