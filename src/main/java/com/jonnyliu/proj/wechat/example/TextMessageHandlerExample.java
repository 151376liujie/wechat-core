package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import org.springframework.stereotype.Component;

/**
 * 文本消息处理器helloworld示例
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 10:07.
 */
@Component
@MessageProcessor(messageType = MessageType.TEXT_MESSAGE)
public class TextMessageHandlerExample extends AbstractMessageHandler {

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        return MessageUtils.buildTextResponseMessage(baseRequestMessage,"hello,world");
    }
}
