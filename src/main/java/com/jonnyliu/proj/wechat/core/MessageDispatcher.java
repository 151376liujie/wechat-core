package com.jonnyliu.proj.wechat.core;

import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;

/**
 * 消息分发器，根据消息的不同来将消息发送给不同的@see{MessageHandler}
 * Created by liujie on 2016/8/6 14:05.
 */
public interface MessageDispatcher {

    /**
     * 将不同类型的消息发送给对应的消息处理器
     *
     * @param msgType 用户发送给公众号的消息类型
     * @return 对应的消息处理器
     */
    AbstractMessageHandler doDispatch(String msgType);

}
