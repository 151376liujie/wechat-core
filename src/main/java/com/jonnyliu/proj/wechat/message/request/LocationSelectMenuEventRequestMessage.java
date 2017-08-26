package com.jonnyliu.proj.wechat.message.request;

import lombok.Data;

/**
 * 选择位置发送菜单事件
 * Author: jonny
 * Time: 2017-08-17 11:36.
 * <xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>
 * <FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>
 * <CreateTime>1408091189</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
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
public class LocationSelectMenuEventRequestMessage extends CustomMenuClickEventRequestMessage {

    private SendLocationInfo SendLocationInfo;
}
