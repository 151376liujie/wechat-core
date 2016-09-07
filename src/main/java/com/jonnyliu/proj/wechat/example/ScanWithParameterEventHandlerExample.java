package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.ScanQrWithParameterEventRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 带参数的二维码扫描事件示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:24.
 */
@Component
@MessageWorker(messageType = MessageType.EVENT,eventType = EventType.EVENT_SCAN)
public class ScanWithParameterEventHandlerExample extends AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScanWithParameterEventHandlerExample.class);

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        ScanQrWithParameterEventRequestMessage scanQrWithParameterEventRequestMessage = (ScanQrWithParameterEventRequestMessage) baseRequestMessage;
        LOGGER.info("带参数的二维码扫描:" + scanQrWithParameterEventRequestMessage.toString());
        return null;
    }
}
