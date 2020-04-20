package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;

/**
 * 事件类型消息基类
 * Author: jonny
 * Time: 2018-02-03 23:49.
 */
@Data
@ToString(callSuper = true)
public class EventRequestMessage extends BaseRequestMessage {

    /**
     * 事件类型
     */
    @XStreamAlias("Event")
    private String event;

    /**
     * 消息类型
     */
    @Override
    public String getMsgType() {
        return MessageType.EVENT.getTypeStr();
    }
}
