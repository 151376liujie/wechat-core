package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.LocationRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;

/**
 * 地理位置消息接收 的 code example
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 13:07.
 */
@MessageWorker(type = MessageType.LOCATION_MESSAGE)
public class LocationMessageHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        if (baseRequestMessage instanceof LocationRequestMessage){
            //在这里实现你自己的业务逻辑
        }
        return null;
    }
}
