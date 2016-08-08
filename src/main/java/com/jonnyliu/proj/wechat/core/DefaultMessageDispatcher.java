package com.jonnyliu.proj.wechat.core;

import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的消息转发器，根据消息类型来转发给不同的消息处理器
 * Created by liujie on 2016/8/6 14:10.
 */
@Component
public class DefaultMessageDispatcher implements MessageDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageDispatcher.class);

    private static final Map<MessageType, Class<? extends AbstractMessageHandler>> messageHandlerMap = new HashMap<>();

    static {
        messageHandlerMap.put(MessageType.TEXT_MESSAGE, TextMessageHandler.class);
        messageHandlerMap.put(MessageType.IMAGE_MESSAGE, ImageMessageHandler.class);
        messageHandlerMap.put(MessageType.VOICE_MESSAGE, VoiceMessageHandler.class);
        messageHandlerMap.put(MessageType.VIDEO_MESSAGE, VideoMessageHandler.class);
        messageHandlerMap.put(MessageType.LOCATION_MESSAGE, LocationMessageHandler.class);
        messageHandlerMap.put(MessageType.LINK_MESSAGE, LinkMessageHandler.class);
        messageHandlerMap.put(MessageType.SHORTVIDEO_MESSAGE, ShortVideoMessageHandler.class);
        messageHandlerMap.put(MessageType.NEWS_MESSAGE, NewsMessageHandler.class);
    }


    public AbstractMessageHandler doDispatch(String msgType) {
        MessageType messageType = MessageType.valueBy(msgType);
        if (messageHandlerMap.containsKey(messageType)) {
            Class<? extends AbstractMessageHandler> aClass = messageHandlerMap.get(messageType);
            try {
                return aClass.newInstance();
            } catch (InstantiationException e) {
                LOGGER.error(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return null;
    }
}
