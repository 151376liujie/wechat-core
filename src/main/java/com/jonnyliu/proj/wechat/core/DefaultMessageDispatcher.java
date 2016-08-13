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
 * author : 980463316@qq.com <br/>
 * Created on 2016/8/6 14:10.
 */
@Component
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
        //得到所有标注MessageWorker注解的类
        Set<Class<? extends AbstractMessageHandler>> messageHandlers = ClassPathUtils.getClassesByAnnotation(MessageWorker.class);
        if (messageHandlers != null && !messageHandlers.isEmpty()) {
            for (Class<? extends AbstractMessageHandler> messageHandlerClass : messageHandlers) {
                MessageWorker annotation = messageHandlerClass.getAnnotation(MessageWorker.class);
                //如果用户发送给公众号的消息类型和消息处理器类型能匹配的上
                if (messageType == annotation.type()) {
                    try {
                        return messageHandlerClass.newInstance();
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                        return null;
                    }
                }
            }
        }
        LOGGER.error("no regested message handler found ...,did you forget to palce MessageWorker on your message Handler ?! ");
        throw new RuntimeException("no regested message handler found ...,did you forget to palce MessageWorker on your message Handler ?! ");
    }
}
