package com.jonnyliu.proj.wechat.message.request;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 扫码带提示事件消息
 * Author: jonny
 * Time: 2017-08-18 14:37.
 */
@Data
public class ScanCodeEventRequestMessage extends BaseRequestMessage {

    /**
     * 事件类型，scancode_waitmsg
     */
    private String Event;

    private String EventKey;

    private ScanCodeInfo ScanCodeInfo = new ScanCodeInfo();

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
