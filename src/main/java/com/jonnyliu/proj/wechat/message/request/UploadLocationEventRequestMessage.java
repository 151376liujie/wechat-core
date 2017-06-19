package com.jonnyliu.proj.wechat.message.request;

import lombok.Data;

/**
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class UploadLocationEventRequestMessage extends BaseRequestMessage {

    /**
     * 事件类型，LOCATION
     */
    private String Event;

    /**
     * 地理位置纬度
     */
    private String Latitude;

    /**
     * 地理位置经度
     */
    private String Longitude;

    /**
     * 地理位置精度
     */
    private String Precision;

}
