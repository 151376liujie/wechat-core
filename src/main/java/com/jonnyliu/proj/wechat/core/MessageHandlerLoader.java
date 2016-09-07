package com.jonnyliu.proj.wechat.core;

import com.google.common.base.Preconditions;
import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.utils.ClassPathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 负责消息处理器类的加载
 * author:980463316@qq.com <br/>
 * Created on 2016-08-20 17:05.
 */
public class MessageHandlerLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerLoader.class);
    private static final Map<MessageType, Class<? extends AbstractMessageHandler>> messageHandlerMappingHolder = new HashMap<>();

    static {
        Set<Class<? extends AbstractMessageHandler>> classesByAnnotation = ClassPathUtils.getClassesByAnnotation(MessageWorker.class);
        Preconditions.checkState(!classesByAnnotation.isEmpty(), "this is no Message Handler in classpath...did you forgot to place a MessageWorker annotation in your MessageHandler class ? ");

        for (Class<? extends AbstractMessageHandler> messageHandler : classesByAnnotation) {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("find message handler :{} with annotation MessageWorker ",messageHandler);
            }
            MessageWorker annotation = messageHandler.getAnnotation(MessageWorker.class);
            //每种类型的消息处理器只能有一个
            if (!messageHandlerMappingHolder.containsKey(annotation.messageType())) {
                messageHandlerMappingHolder.put(annotation.messageType(), messageHandler);
            }else{
                if (LOGGER.isDebugEnabled()){
                    LOGGER.debug("found duplicate message handler :{} with annotation :{},will ignore it !",messageHandler,annotation);
                }
            }
        }
    }


    public static Map<MessageType, Class<? extends AbstractMessageHandler>> getMessageHandlerMappingHolder() {
        return messageHandlerMappingHolder;
    }
}
