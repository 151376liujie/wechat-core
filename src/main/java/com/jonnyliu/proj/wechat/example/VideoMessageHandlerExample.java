package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.VideoRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;

/**
 * 图片消息接收和响应的code example
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 11:42.
 */
@MessageWorker(type = MessageType.VIDEO_MESSAGE)
public class VideoMessageHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        if (baseRequestMessage instanceof VideoRequestMessage){
            //在这里实现你自己的业务逻辑

        }
        return null;
    }
}
