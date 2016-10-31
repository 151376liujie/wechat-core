package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.CustomMenuClickOrViewEventRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import org.springframework.stereotype.Component;

/**
 * 自定义菜单跳转事件示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:37.
 */
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_CUSTOM_MENU_CLICK)
public class CustomMenuClickEventHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        CustomMenuClickOrViewEventRequestMessage customMenuClickOrViewEventRequestMessage = (CustomMenuClickOrViewEventRequestMessage) baseRequestMessage;

        //在这里实现你自己的业务逻辑

        return null;

    }
}
