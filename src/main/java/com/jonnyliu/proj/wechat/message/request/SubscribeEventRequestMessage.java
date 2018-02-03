package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.EventType;
import lombok.Data;

/**
 * 订阅消息封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class SubscribeEventRequestMessage extends EventRequestMessage {


    @Override
    public String getEvent() {
        return EventType.EVENT_SUBSCRIBE.getTypeStr();
    }
}
