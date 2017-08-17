package com.jonnyliu.proj.wechat.message.request;

import lombok.Data;

/**
 * 选择位置发送菜单事件
 * Author: jonny
 * Time: 2017-08-17 11:36.
 */
@Data
public class LocationSelectMenuEventRequestMessage extends CustomMenuClickOrViewEventRequestMessage {

    private String SendLocationInfo;
}
