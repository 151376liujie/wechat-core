package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.EventType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 扫码带提示事件消息
 * Author: jonny
 * Time: 2017-08-18 14:37.
 */
@Data
@XStreamAlias("xml")
@ToString(callSuper = true)
public class ScanCodeEventRequestMessage extends EventRequestMessage {

    @XStreamAlias("EventKey")
    private String eventKey;

    @XStreamAlias("ScanCodeInfo")
    private ScanCodeInfo scanCodeInfo = new ScanCodeInfo();

    @Override
    public String getEvent() {
        return EventType.EVENT_SCAN_CODE_WAIT_MSG.getTypeStr();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
