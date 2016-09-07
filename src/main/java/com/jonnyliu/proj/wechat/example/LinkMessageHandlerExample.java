package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.LinkRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import org.springframework.stereotype.Component;

/**
 * 接收链接消息的code example
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 13:09.
 */
@Component
@MessageWorker(messageType = MessageType.LINK_MESSAGE)
public class LinkMessageHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        //在这里实现你自己的业务逻辑
        LinkRequestMessage linkRequestMessage = (LinkRequestMessage) baseRequestMessage;
        String content = "您发送的链接消息如下：title:%s,url:%s,description:%s ";
        content = String.format(content,linkRequestMessage.getTitle(),linkRequestMessage.getUrl(),linkRequestMessage.getDescription());
        return MessageUtils.buildTextResponseMessage(baseRequestMessage,content);
    }
}
