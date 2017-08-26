package com.jonnyliu.proj.wechat.core;

import com.google.common.base.Preconditions;
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
public class DefaultMessageDispatcher implements MessageDispatcher {

    /**
     * 根据注解来绑定不同类型消息的对应消息处理器
     *
     * @param eventType 消息事件类型，只有当msgType == "Event" 时该值才有意义
     * @param msgType   用户发送给公众号的消息类型
     * @return 返回消息处理器
     */
    public AbstractMessageHandler doDispatch(String msgType, String eventType) {
        MessageType messageType = MessageType.valueBy(msgType);
        Preconditions.checkNotNull(msgType, "invalid message messageType !");

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
