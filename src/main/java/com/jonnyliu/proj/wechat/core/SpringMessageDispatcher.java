package com.jonnyliu.proj.wechat.core;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 从spring容器中查找消息处理器
 * author:980463316@qq.com
 * Created on 2016-09-06 20:59.
 */
@Component
public class SpringMessageDispatcher implements MessageDispatcher, ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMessageDispatcher.class);

    private ApplicationContext context;

    @Override
    public AbstractMessageHandler doDispatch(String msgType) {
        MessageType messageType = MessageType.valueBy(msgType);
        if (messageType == null) {
            throw new RuntimeException("unknow message messageType : " + msgType);
        }

        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(MessageWorker.class);
        if (beansWithAnnotation == null || beansWithAnnotation.isEmpty()) {
            throw new RuntimeException("this is no class annotationed with @MessageWorker,do you forgot ??");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("found classes that annotationed with {} : {}", MessageWorker.class.getSimpleName(), beansWithAnnotation);
        }

        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            Object messageHandlerInstance = entry.getValue();
            Class<?> messageHandlerClass = messageHandlerInstance.getClass();
            if (!AbstractMessageHandler.class.isAssignableFrom(messageHandlerClass)) {
                continue;
            }
            MessageWorker annotation = messageHandlerClass.getAnnotation(MessageWorker.class);
            if (annotation.messageType() != messageType) {
                continue;
            }
            return (AbstractMessageHandler) messageHandlerInstance;
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
