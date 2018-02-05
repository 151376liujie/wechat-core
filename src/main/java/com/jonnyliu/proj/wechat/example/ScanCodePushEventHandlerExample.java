package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.ScanCodePushEventRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 扫码带提示事件处理器
 * Author: jonny
 * Time: 2017-08-18 14:48.
 */
@Slf4j
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_SCAN_CODE_PUSH)
public class ScanCodePushEventHandlerExample extends AbstractMessageHandler {

    /**
     * 真正的处理消息的方法
     *
     * @param baseRequestMessage
     * @return
     */
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {

        ScanCodePushEventRequestMessage scanCodePushEventRequestMessage = (ScanCodePushEventRequestMessage) baseRequestMessage;
        String eventKey = scanCodePushEventRequestMessage.getEventKey();
        if (eventKey.equalsIgnoreCase(WechatConstant.MENU_SCAN_CODE_PUSH)) {
            if (log.isInfoEnabled()) {
                log.info("{} 扫描二维码的结果是: {}", scanCodePushEventRequestMessage.getFromUserName(), scanCodePushEventRequestMessage.getScanCodeInfo());
            }
            String template = "您的扫描结果是:%s";
            return MessageUtils.buildTextResponseMessage(baseRequestMessage, String.format(template, scanCodePushEventRequestMessage.getScanCodeInfo().getScanResult()));
        }

        return null;
    }
}
