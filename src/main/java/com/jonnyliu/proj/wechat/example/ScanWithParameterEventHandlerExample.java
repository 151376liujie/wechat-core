package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.ScanQrWithParameterEventRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 带参数的二维码扫描事件示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:24.
 */
@Slf4j
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_SCAN)
public class ScanWithParameterEventHandlerExample extends AbstractMessageHandler {

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        ScanQrWithParameterEventRequestMessage scanQrWithParameterEventRequestMessage = (ScanQrWithParameterEventRequestMessage) baseRequestMessage;
        if (log.isInfoEnabled()) {
            log.info("带参数的二维码扫描:" + scanQrWithParameterEventRequestMessage.toString());
        }
        return null;
    }
}
