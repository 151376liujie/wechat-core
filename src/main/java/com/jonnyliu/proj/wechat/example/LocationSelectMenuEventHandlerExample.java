package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.LocationSelectMenuEventRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 发送位置菜单事件示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:37.
 */
@Slf4j
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_LOCATION_SELECT)
public class LocationSelectMenuEventHandlerExample extends AbstractMessageHandler {

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {

        LocationSelectMenuEventRequestMessage locationSelectMenuEventRequestMessage = (LocationSelectMenuEventRequestMessage) baseRequestMessage;

        String eventKey = locationSelectMenuEventRequestMessage.getEventKey();

        //用户点击了发送位置菜单
        if (WechatConstant.MENU_LOCATION_SELECT_KEY.equalsIgnoreCase(eventKey)) {
            if (log.isInfoEnabled()) {
                log.info("{} 发送了一个位置给我", locationSelectMenuEventRequestMessage.getFromUserName());
            }
            return MessageUtils.buildTextResponseMessage(baseRequestMessage, "您点击了[发送位置按钮]");
        }

        return null;

    }
}
