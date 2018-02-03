package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;
import lombok.Data;

/**
 * 对地理位置消息的封装
 * Created by jonnyliu-ds8 on 2016/8/5.
 */
@Data
public class LocationRequestMessage extends CommonRequestMessage {

    /**
     * 地理位置维度
     */
    private String Location_X;

    /**
     * 地理位置经度
     */
    private String Location_Y;

    /**
     * 地图缩放大小
     */
    private String Scale;

    /**
     * 地理位置信息
     */
    private String Label;

    @Override
    public String getMsgType() {
        return MessageType.LOCATION_MESSAGE.getTypeStr();
    }

}
