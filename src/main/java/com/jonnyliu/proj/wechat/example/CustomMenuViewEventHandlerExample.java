package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.CustomMenuClickEventRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 自定义菜单查看事件示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:37.
 */
@Slf4j
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_CUSTOM_MENU_VIEW)
public class CustomMenuViewEventHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        CustomMenuClickEventRequestMessage customMenuClickEventRequestMessage = (CustomMenuClickEventRequestMessage) baseRequestMessage;

        //在这里实现你自己的业务逻辑
        log.info("{} 点击了[view]类型的菜单,eventKey={}", customMenuClickEventRequestMessage.getFromUserName(), customMenuClickEventRequestMessage.getEventKey());
        return null;

    }
}
