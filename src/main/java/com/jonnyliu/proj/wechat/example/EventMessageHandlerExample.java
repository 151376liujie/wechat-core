package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.*;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 事件消息处理器示例
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-22 13:47.
 */
@MessageWorker(type = MessageType.EVENT)
public class EventMessageHandlerExample extends AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventMessageHandlerExample.class);

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        //（取消）关注事件消息
        if (baseRequestMessage instanceof SubOrUnSubEventRequestMessage) {

            SubOrUnSubEventRequestMessage subOrUnSubEventRequestMessage = (SubOrUnSubEventRequestMessage) baseRequestMessage;
            String event = subOrUnSubEventRequestMessage.getEvent();
            EventType eventType = EventType.valueBy(event);
            //关注事件
            if (eventType == EventType.EVENT_SUBSCRIBE) {
                return MessageUtils.buildTextResponseMessage(baseRequestMessage, "感谢您的关注！<a href=\"https://github.com/151376liujie/wechat-core\">微信开源项目地址</a>");
            } else if (eventType == EventType.EVENT_UNSUBSCRIBE) {//取消关注事件
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("用户：[{}] 取消了对公众号的关注！", subOrUnSubEventRequestMessage.getFromUserName());
                }
            }
        } else if (baseRequestMessage instanceof UploadLocationEventRequestMessage) {
            //用户上传地理位置信息事件类型
            UploadLocationEventRequestMessage uploadLocationEventRequestMessage = (UploadLocationEventRequestMessage) baseRequestMessage;
            //在这里实现你自己的业务逻辑

        } else if (baseRequestMessage instanceof ScanQrWithParameterEventRequestMessage) {
            //扫描二维码事件
            ScanQrWithParameterEventRequestMessage scanQrWithParameterEventRequestMessage = (ScanQrWithParameterEventRequestMessage) baseRequestMessage;
            String event = scanQrWithParameterEventRequestMessage.getEvent();
            EventType eventType = EventType.valueBy(event);
            //扫描时未关注公众号的事件
            if (eventType == EventType.EVENT_SCAN_SUBSCRIBE) {
                LOGGER.info("扫描时未关注公众号:" + scanQrWithParameterEventRequestMessage.toString());
            } else if (eventType == EventType.EVENT_SCAN) {//带参数的二维码扫描事件类型
                LOGGER.info("带参数的二维码扫描:" + scanQrWithParameterEventRequestMessage.toString());
            }
        } else if (baseRequestMessage instanceof CustomMenuClickOrViewEventRequestMessage) {
            CustomMenuClickOrViewEventRequestMessage customMenuClickOrViewEventRequestMessage = (CustomMenuClickOrViewEventRequestMessage) baseRequestMessage;
            String event = customMenuClickOrViewEventRequestMessage.getEvent();
            EventType eventType = EventType.valueBy(event);
            //自定义菜单点击事件类型
            if (eventType == EventType.EVENT_CUSTOM_MENU_CLICK) {

            } else if (eventType == EventType.EVENT_CUSTOM_MENU_VIEW) {//自定义菜单跳转事件类型

            }
        } else {
            LOGGER.error("no message type: [{}] found.", baseRequestMessage);
        }
        return null;
    }
}
