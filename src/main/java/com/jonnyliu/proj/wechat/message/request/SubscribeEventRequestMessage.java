package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.EventType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;

/**
 * 订阅消息封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
@ToString(callSuper = true)
@XStreamAlias("xml")
public class SubscribeEventRequestMessage extends EventRequestMessage {

    @Override
    public String getEvent() {
        return EventType.EVENT_SUBSCRIBE.getTypeStr();
    }
}
