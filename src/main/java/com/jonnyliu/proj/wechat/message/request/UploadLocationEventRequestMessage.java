package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.EventType;
import lombok.Data;

/**
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class UploadLocationEventRequestMessage extends EventRequestMessage {

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

    @Override
    public String getEvent() {
        return EventType.EVENT_UPLOAD_LOCATION.getTypeStr();
    }
}
