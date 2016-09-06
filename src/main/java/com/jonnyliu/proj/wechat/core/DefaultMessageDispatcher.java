package com.jonnyliu.proj.wechat.core;

import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 默认的消息转发器，根据消息类型来转发给不同的消息处理器
 * author : 980463316@qq.com <br/>
 * Created on 2016/8/6 14:10.
 */
//@Component
public class DefaultMessageDispatcher implements MessageDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageDispatcher.class);

    /**
     * 根据注解来绑定不同类型消息的对应消息处理器
     *
     * @param msgType 用户发送给公众号的消息类型
     * @return 返回消息处理器
     */
    public AbstractMessageHandler doDispatch(String msgType) {
        MessageType messageType = MessageType.valueBy(msgType);
        if (messageType == null) {
            LOGGER.error("invalid message type : {}", msgType);
            throw new RuntimeException("invalid message type [" + msgType + "]");
        }

        Map<MessageType, Class<? extends AbstractMessageHandler>> messageHandlerMappingHolder = MessageHandlerLoader.getMessageHandlerMappingHolder();
        Class<? extends AbstractMessageHandler> messageHandlerClass = messageHandlerMappingHolder.get(messageType);
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("found message handler :{}", messageHandlerClass);
            }
            return messageHandlerClass.newInstance();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
