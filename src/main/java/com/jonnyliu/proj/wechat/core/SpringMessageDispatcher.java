package com.jonnyliu.proj.wechat.core;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.enums.EventType;
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
    public AbstractMessageHandler doDispatch(String msgType, String eventType) {
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("message type is : {},and event type is : {}",msgType,eventType);
        }
        MessageType messageType = MessageType.valueBy(msgType);
        Preconditions.checkNotNull(messageType, "unknow messageType ");

        EventType eventTyp = null;
        if (!Strings.isNullOrEmpty(eventType)){
            eventTyp = EventType.valueBy(eventType);
            Preconditions.checkNotNull(eventTyp, "unknow eventType !");
        }

        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(MessageProcessor.class);
        if (beansWithAnnotation == null || beansWithAnnotation.isEmpty()) {
            throw new RuntimeException("this is no class annotationed with @MessageProcessor,do you forgot ??");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("found classes that annotationed with {} : {}", MessageProcessor.class.getSimpleName(), beansWithAnnotation);
        }

        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            Object messageHandlerInstance = entry.getValue();
            Class<?> messageHandlerClass = messageHandlerInstance.getClass();
            //消息处理器类必须是abstractMessageHandler的子类
            if (!AbstractMessageHandler.class.isAssignableFrom(messageHandlerClass)) {
                continue;
            }
            MessageProcessor annotation = messageHandlerClass.getAnnotation(MessageProcessor.class);

            //事件类型
            if (annotation.messageType() == MessageType.EVENT ){
                if (annotation.eventType() == eventTyp){
                    return (AbstractMessageHandler) messageHandlerInstance;
                }
            }else{
                //普通类型
                if(annotation.messageType() == messageType){
                    return (AbstractMessageHandler) messageHandlerInstance;
                }
            }
        }
        LOGGER.error("no message handler found ,messageType :{},eventType :{}",msgType,eventType);
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
