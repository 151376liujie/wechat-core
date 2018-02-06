package com.jonnyliu.proj.wechat.core;

import com.google.common.base.Preconditions;
import com.jonnyliu.proj.wechat.bean.MessageHandlerElement;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 默认的消息转发器，根据消息类型来转发给不同的消息处理器(没有从spring容器中拿而是自己从类路径下查找)
 * author : 980463316@qq.com <br/>
 * Created on 2016/8/6 14:10.
 */
@Slf4j
public class DefaultMessageHandlerAdapter implements MessageHandlerAdapter {

    /**
     * 根据注解来绑定不同类型消息的对应消息处理器
     *
     * @param messageHandlerElement 包含消息类型和消息的事件类型
     * @return 返回消息处理器
     */
    @Override
    public AbstractMessageHandler findMessageHandler(MessageHandlerElement messageHandlerElement) {
        MessageType messageType = MessageType.valueBy(messageHandlerElement.getMessageType());
        Preconditions.checkNotNull(messageHandlerElement.getMessageType(), "invalid message messageType !");

        Map<MessageType, Class<? extends AbstractMessageHandler>> messageHandlerMappingHolder = MessageHandlerLoader.getMessageHandlerMappingHolder();
        Class<? extends AbstractMessageHandler> messageHandlerClass = messageHandlerMappingHolder.get(messageType);
        try {
            if (log.isDebugEnabled()) {
                log.debug("found message handler :{}", messageHandlerClass);
            }
            return messageHandlerClass.newInstance();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
