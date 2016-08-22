package com.jonnyliu.proj.wechat.core;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.utils.ClassPathUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 负责消息处理器类的加载
 * author:980463316@qq.com <br/>
 * Created on 2016-08-20 17:05.
 */
public class MessageHandlerLoader {


    private static final Map<MessageType, Class<? extends AbstractMessageHandler>> messageHandlerMappingHolder = new HashMap<>();

    static {
        Set<Class<? extends AbstractMessageHandler>> classesByAnnotation = ClassPathUtils.getClassesByAnnotation(MessageWorker.class);
        if (classesByAnnotation.isEmpty()) {
            throw new RuntimeException("this is no Message Handler in classpath...do you forgot to place a MessageWorker annotation in your MessageHandler class ? ");
        }
        for (Class<? extends AbstractMessageHandler> messageHandler : classesByAnnotation) {
            MessageWorker annotation = messageHandler.getAnnotation(MessageWorker.class);
            //每种类型的消息处理器只能有一个
            if (!messageHandlerMappingHolder.containsKey(annotation.type())) {
                messageHandlerMappingHolder.put(annotation.type(), messageHandler);
            }
        }
    }


    public static Map<MessageType, Class<? extends AbstractMessageHandler>> getMessageHandlerMappingHolder() {
        return messageHandlerMappingHolder;
    }
}
