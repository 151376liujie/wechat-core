package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.TextRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;

/**
 * 文本消息处理器helloworld示例
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 10:07.
 */

@MessageWorker(type = MessageType.TEXT_MESSAGE)
public class TextMessageHandlerExample extends AbstractMessageHandler {

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        if (baseRequestMessage instanceof TextRequestMessage){
            return MessageUtils.buildTextResponseMessage(baseRequestMessage,"hello,world");
        }
        return null;
    }
}
