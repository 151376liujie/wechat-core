package com.jonnyliu.proj.wechat.handler;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.TextRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文本消息处理器事例 <br/>
 * author : 980463316@qq.com
 * Created on 2016/8/6 10:57.
 */
@MessageWorker(type = MessageType.TEXT_MESSAGE)
public class TextMessageHandler extends AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextMessageHandler.class);

    public BaseResponseMessage doHandleMessage(BaseRequestMessage requestMessage) {
        if (requestMessage instanceof TextRequestMessage) {
            //在这里实现你自己的业务逻辑
            return MessageUtils.buildTextResponseMessage(requestMessage, "hello,world");
        }
        return null;
    }

}
