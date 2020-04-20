package com.jonnyliu.proj.wechat.message.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;

/**
 * 选择位置发送菜单事件
 * Author: jonny
 * Time: 2017-08-17 11:36.
 * <xml><toUserName><![CDATA[gh_e136c6e50636]]></toUserName>
 * <fromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></fromUserName>
 * <createTime>1408091189</createTime>
 * <msgType><![CDATA[event]]></msgType>
 * <Event><![CDATA[location_select]]></Event>
 * <EventKey><![CDATA[6]]></EventKey>
 * <SendLocationInfo>
 * <Location_X><![CDATA[23]]></Location_X>
 * <Location_Y><![CDATA[113]]></Location_Y>
 * <Scale><![CDATA[15]]></Scale>
 * <Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label>
 * <Poiname><![CDATA[]]></Poiname>
 * </SendLocationInfo>
 * </xml>
 */
@Data
@XStreamAlias("xml")
@ToString(callSuper = true)
public class LocationSelectMenuEventRequestMessage extends EventRequestMessage {

    @XStreamAlias("EventKey")
    private String eventKey;

    @XStreamAlias("SendLocationInfo")
    private SendLocationInfo sendLocationInfo;
}
