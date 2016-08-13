package com.jonnyliu.proj.wechat.core;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.utils.ClassPathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 默认的消息转发器，根据消息类型来转发给不同的消息处理器
 * Created by liujie on 2016/8/6 14:10.
 */
@Component
public class DefaultMessageDispatcher implements MessageDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageDispatcher.class);

    public AbstractMessageHandler doDispatch(String msgType) {
        MessageType messageType = MessageType.valueBy(msgType);
        Set<Class<? extends AbstractMessageHandler>> messageHandlers = ClassPathUtils.getClassesByAnnotation(MessageWorker.class);
        if (messageHandlers != null && !messageHandlers.isEmpty()) {
            for (Class<? extends AbstractMessageHandler> messageHandlerClass : messageHandlers) {
                MessageWorker annotation = messageHandlerClass.getAnnotation(MessageWorker.class);
                if (messageType == annotation.type()) {
                    try {
                        return messageHandlerClass.newInstance();
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        }
        return null;
    }
}
