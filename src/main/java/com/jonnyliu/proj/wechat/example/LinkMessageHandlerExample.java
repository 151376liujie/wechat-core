package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.LinkRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;

/**
 * 接收链接消息的code example
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 13:09.
 */
@MessageWorker(type = MessageType.LINK_MESSAGE)
public class LinkMessageHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        if (baseRequestMessage instanceof LinkRequestMessage){
            //在这里实现你自己的业务逻辑
        }
        return null;
    }
}
