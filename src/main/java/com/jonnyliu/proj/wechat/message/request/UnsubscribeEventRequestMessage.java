package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.EventType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;

/**
 * 订阅(取消订阅)的消息封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("xml")
@ToString(callSuper = true)
public class UnsubscribeEventRequestMessage extends EventRequestMessage {

    @Override
    public String getEvent() {
        return EventType.EVENT_UNSUBSCRIBE.getTypeStr();
    }
}
