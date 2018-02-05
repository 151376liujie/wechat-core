package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.EventType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("xml")
public class UploadLocationEventRequestMessage extends EventRequestMessage {

    /**
     * 地理位置纬度
     */
    @XStreamAlias("Latitude")
    private String latitude;

    /**
     * 地理位置经度
     */
    @XStreamAlias("Longitude")
    private String longitude;

    /**
     * 地理位置精度
     */
    @XStreamAlias("Precision")
    private String precision;

    @Override
    public String getEvent() {
        return EventType.EVENT_UPLOAD_LOCATION.getTypeStr();
    }
}
