package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.SubOrUnSubEventRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 取消关注事件处理器示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:11.
 */
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_UNSUBSCRIBE)
public class UnSubscribeEventHandlerExample extends AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnSubscribeEventHandlerExample.class);

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        SubOrUnSubEventRequestMessage unSubEventRequestMessage = (SubOrUnSubEventRequestMessage) baseRequestMessage;
        String fromUserName = unSubEventRequestMessage.getFromUserName();
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn("用户：[{}] 取消了对公众号的关注！", fromUserName);
        }
        return null;
    }
}
