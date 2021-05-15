package com.jonnyliu.proj.wechat.message.request;

/**
 * Author: jonny
 * Time: 2018-02-05 22:22.
 */

import com.jonnyliu.proj.wechat.enums.EventType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;

/**
 * <xml><toUserName><![CDATA[gh_e136c6e50636]]></toUserName>
 * <fromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></fromUserName>
 * <createTime>1408090502</createTime>
 * <msgType><![CDATA[event]]></msgType>
 * <Event><![CDATA[scancode_push]]></Event>
 * <EventKey><![CDATA[6]]></EventKey>
 * <ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>
 * <ScanResult><![CDATA[1]]></ScanResult>
 * </ScanCodeInfo>
 * </xml>
 */
@Data
@XStreamAlias("xml")
@ToString(callSuper = true)
public class ScanCodePushEventRequestMessage extends EventRequestMessage {

    @XStreamAlias("EventKey")
    private String eventKey;

    @XStreamAlias("ScanCodeInfo")
    private ScanCodeInfo scanCodeInfo = new ScanCodeInfo();

    @Override
    public String getEvent() {
        return EventType.EVENT_SCAN_CODE_PUSH.getTypeStr();
    }
}
