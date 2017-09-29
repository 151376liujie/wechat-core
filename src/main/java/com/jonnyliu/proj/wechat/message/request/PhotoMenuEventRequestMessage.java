package com.jonnyliu.proj.wechat.message.request;

import lombok.Data;

/**
 * 系统拍照发图菜单事件
 * Author: jonny
 * Time: 2017-08-17 11:36.
 */
@Data
public class PhotoMenuEventRequestMessage extends CustomMenuClickEventRequestMessage {
    /**
     * 发送的图片信息
     */
    private SendPicsInfo SendPicsInfo = new SendPicsInfo();
}
