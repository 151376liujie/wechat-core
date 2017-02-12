package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.manager.JokeManager;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.CustomMenuClickOrViewEventRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义菜单跳转事件示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:37.
 */
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_CUSTOM_MENU_CLICK)
public class CustomMenuClickEventHandlerExample extends AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomMenuClickEventHandlerExample.class);

    @Autowired
    private JokeManager jokeManager;

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {

        CustomMenuClickOrViewEventRequestMessage customMenuClickOrViewEventRequestMessage = (CustomMenuClickOrViewEventRequestMessage) baseRequestMessage;

        //用户要听笑话
        if (WechatConstant.MENU_JOKE_CLICK_KEY.equalsIgnoreCase(customMenuClickOrViewEventRequestMessage.getEventKey())) {

            String joke = jokeManager.getOneJoke(customMenuClickOrViewEventRequestMessage.getFromUserName());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("joke is {}",joke);
            }
            return MessageUtils.buildTextResponseMessage(baseRequestMessage,joke);
        }

        return null;

    }
}
