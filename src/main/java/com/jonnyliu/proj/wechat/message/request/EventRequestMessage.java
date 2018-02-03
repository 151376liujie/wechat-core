package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;
import lombok.Data;

/**
 * 事件类型消息基类
 * Author: jonny
 * Time: 2018-02-03 23:49.
 */
@Data
public class EventRequestMessage extends BaseRequestMessage {

    /**
     * 事件类型
     */
    private String Event;

    /**
     * 消息类型
     */
    @Override
    public String getMsgType() {
        return MessageType.EVENT.getTypeStr();
    }
}
