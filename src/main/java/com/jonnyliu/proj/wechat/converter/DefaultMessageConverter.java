package com.jonnyliu.proj.wechat.converter;

import com.google.common.base.Preconditions;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.message.request.*;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 默认的消息转换器，将用户发送至公众号的消息xml字符串转换为消息对象
 * author : 980463316@qq.com <br/>
 * Created on 2016/8/6 15:31.
 */
@Slf4j
@Component
public class DefaultMessageConverter implements MessageConvert {

    @Override
    public BaseRequestMessage doConvert(String xml) throws Exception {
        if (log.isInfoEnabled()) {
            log.info("request xml message : {}", xml);
        }
        String messageTypeStr = MessageUtils.getMessageType(xml);
        if (messageTypeStr == null) {
            //通过正则表达式没有找到<msgType>
            log.error("failed to recognize message type!!!");
            return null;
        }
        MessageType messageType = MessageType.valueBy(messageTypeStr);
        Preconditions.checkNotNull(messageType, "no msgType found!");
        switch (messageType) {
            //文本消息
            case TEXT_MESSAGE:
                return MessageUtils.xml2Message(xml, TextRequestMessage.class);
            //链接消息
            case LINK_MESSAGE:
                return MessageUtils.xml2Message(xml, LinkRequestMessage.class);
            //图片消息
            case IMAGE_MESSAGE:
                return MessageUtils.xml2Message(xml, ImageRequestMessage.class);
            //语音消息
            case VOICE_MESSAGE:
                return MessageUtils.xml2Message(xml, VoiceRequestMessage.class);
            //短视频消息
            case SHORT_VIDEO_MESSAGE:
                return MessageUtils.xml2Message(xml, ShortVideoRequestMessage.class);
            //视频消息
            case VIDEO_MESSAGE:
                return MessageUtils.xml2Message(xml, VideoRequestMessage.class);
            //地理位置消息
            case LOCATION_MESSAGE:
                return MessageUtils.xml2Message(xml, LocationRequestMessage.class);
            case EVENT:
                String event = MessageUtils.getEventType(xml);
                EventType eventType = EventType.valueBy(event);
                Preconditions.checkNotNull(eventType, "no event message messageType found!");
                if (log.isDebugEnabled()) {
                    log.debug("convert to {} message object!", eventType.getTypeStr());
                }
                switch (eventType) {
                    case EVENT_SUBSCRIBE:
                        return MessageUtils.xml2Message(xml, SubscribeEventRequestMessage.class);
                    case EVENT_UNSUBSCRIBE:
                        return MessageUtils.xml2Message(xml, UnsubscribeEventRequestMessage.class);
                    //扫描二维码时未关注公众号消息
                    case EVENT_SCAN_SUBSCRIBE:
                        //扫描二维码时已关注公众号消息
                    case EVENT_SCAN:
                        return MessageUtils.xml2Message(xml, ScanQrWithParameterEventRequestMessage.class);
                    case EVENT_SCAN_CODE_WAIT_MSG:
                        return MessageUtils.xml2Message(xml, ScanCodeEventRequestMessage.class);
                    case EVENT_SCAN_CODE_PUSH:
                        return MessageUtils.xml2Message(xml, ScanCodePushEventRequestMessage.class);
                    case EVENT_UPLOAD_LOCATION:
                        return MessageUtils.xml2Message(xml, UploadLocationEventRequestMessage.class);
                    case EVENT_CUSTOM_MENU_CLICK:
                        return MessageUtils.xml2Message(xml, CustomMenuClickEventRequestMessage.class);
                    case EVENT_CUSTOM_MENU_VIEW:
                        return MessageUtils.xml2Message(xml, CustomMenuViewEventRequestMessage.class);
                    case EVENT_LOCATION_SELECT:
                        return MessageUtils.xml2Message(xml, LocationSelectMenuEventRequestMessage.class);
                    case EVENT_PIC_SYS_PHOTO:
                    case EVENT_PIC_PHOTO_OR_ALBUM:
                    case EVENT_PIC_WEIXIN:
                        return MessageUtils.xml2Message(xml, PhotoMenuEventRequestMessage.class);
                    default:
                        log.warn("未知事件类型:{}", event);
                        return null;
                }
            default:
                log.warn("未知消息类型:{}", messageTypeStr);
                return null;
        }
    }

}
