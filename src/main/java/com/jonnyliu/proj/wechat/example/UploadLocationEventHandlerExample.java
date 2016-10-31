package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.UploadLocationEventRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import org.springframework.stereotype.Component;

/**
 * 用户上传地理位置信息事件类型
 * author:980463316@qq.com
 * Created on 2016-09-07 23:21.
 */
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_UPLOAD_LOCATION)
public class UploadLocationEventHandlerExample extends AbstractMessageHandler {

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        UploadLocationEventRequestMessage uploadLocationEventRequestMessage = (UploadLocationEventRequestMessage) baseRequestMessage;
        //在这里实现你自己的业务逻辑

        return null;
    }
}
