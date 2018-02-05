package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 对地理位置消息的封装
 * Created by jonnyliu-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("xml")
public class LocationRequestMessage extends CommonRequestMessage {

    /**
     * 地理位置维度
     */
    @XStreamAlias("Location_X")
    private String location_X;

    /**
     * 地理位置经度
     */
    @XStreamAlias("Location_Y")
    private String location_Y;

    /**
     * 地图缩放大小
     */
    @XStreamAlias("Scale")
    private String scale;

    /**
     * 地理位置信息
     */
    @XStreamAlias("Label")
    private String label;

    @Override
    public String getMsgType() {
        return MessageType.LOCATION_MESSAGE.getTypeStr();
    }

}
