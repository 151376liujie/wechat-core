package com.jonnyliu.proj.wechat.core;

import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.MessageHandler;
import com.jonnyliu.proj.wechat.handler.TextMessageHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liujie on 2016/8/6 14:22.
 */
public class MessageHandlerMapping {

    private static final Map<MessageType, MessageHandler> handlerMapper = new HashMap<>();

    private static TextMessageHandler textMessageHandler = new TextMessageHandler();


    static {
        handlerMapper.put(MessageType.TEXT_MESSAGE, textMessageHandler);

    }


}
