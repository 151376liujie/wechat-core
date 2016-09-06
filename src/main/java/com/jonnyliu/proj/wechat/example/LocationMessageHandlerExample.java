package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.LocationRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;

/**
 * 地理位置消息接收 的 code example
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 13:07.
 */
@MessageWorker(messageType = MessageType.LOCATION_MESSAGE)
public class LocationMessageHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        if (baseRequestMessage instanceof LocationRequestMessage){
            //在这里实现你自己的业务逻辑
            LocationRequestMessage locationRequestMessage = (LocationRequestMessage) baseRequestMessage;
            String content = "您发送的地理位置消息如下：label:%s,location_x:%s,location_y:%s,scale:%s ";
            content = String.format(content,locationRequestMessage.getLabel(),locationRequestMessage.getLocation_X(),locationRequestMessage.getLocation_Y(),locationRequestMessage.getScale());
            return MessageUtils.buildTextResponseMessage(baseRequestMessage,content);
        }
        return null;
    }
}
