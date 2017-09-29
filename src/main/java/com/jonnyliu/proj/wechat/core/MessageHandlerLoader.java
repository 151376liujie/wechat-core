package com.jonnyliu.proj.wechat.core;

import com.google.common.base.Preconditions;
import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.utils.ClassPathUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 负责消息处理器类的加载
 * author:980463316@qq.com <br/>
 * Created on 2016-08-20 17:05.
 */
@Slf4j
public class MessageHandlerLoader {

    private static final Map<MessageType, Class<? extends AbstractMessageHandler>> messageHandlerMappingHolder = new ConcurrentHashMap<>();

    static {
        Set<Class<? extends AbstractMessageHandler>> classesByAnnotation = ClassPathUtils.getClassesByAnnotation(MessageProcessor.class);
        Preconditions.checkState(!classesByAnnotation.isEmpty(), "this is no Message Handler in classpath...did you forgot to place a MessageProcessor annotation in your MessageHandler class ? ");

        for (Class<? extends AbstractMessageHandler> messageHandler : classesByAnnotation) {
            if (log.isDebugEnabled()) {
                log.debug("find message handler :{} with annotation MessageProcessor ", messageHandler);
            }
            MessageProcessor annotation = messageHandler.getAnnotation(MessageProcessor.class);
            //每种类型的消息处理器只能有一个
            if (!messageHandlerMappingHolder.containsKey(annotation.messageType())) {
                messageHandlerMappingHolder.put(annotation.messageType(), messageHandler);
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("found duplicate message handler :{} with annotation :{},will ignore it !", messageHandler, annotation);
                }
            }
        }
    }


    public static Map<MessageType, Class<? extends AbstractMessageHandler>> getMessageHandlerMappingHolder() {
        return messageHandlerMappingHolder;
    }
}
