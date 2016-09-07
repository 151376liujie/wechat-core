package com.jonnyliu.proj.wechat.converter;

import com.google.common.base.Preconditions;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.message.request.*;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 默认的消息转换器，将用户发送至公众号的消息xml字符串转换为消息对象
 * author : 980463316@qq.com <br/>
 * Created on 2016/8/6 15:31.
 */
@Component
public class DefaultMessageConverter implements MessageConvert {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageConverter.class);

    @Override
    public BaseRequestMessage doConvert(Map<String, String> xmlMap) {

        MessageType messageType = MessageType.valueBy(xmlMap.get("MsgType"));
        Preconditions.checkNotNull(messageType, "no MsgType found!");
        switch (messageType) {
            //文本消息
            case TEXT_MESSAGE:
                String content = xmlMap.get("Content");
                TextRequestMessage textRequestMessage = new TextRequestMessage();
                //填充消息对象
                MessageUtils.inflateBaseRequestMessage(xmlMap, textRequestMessage);
                textRequestMessage.setMsgId(Long.parseLong(xmlMap.get("MsgId")));
                textRequestMessage.setContent(content);
                return textRequestMessage;
            //图片消息
            case IMAGE_MESSAGE:
                String mediaId = xmlMap.get("MediaId");
                String picUrl = xmlMap.get("PicUrl");
                ImageRequestMessage imageRequestMessage = new ImageRequestMessage();
                MessageUtils.inflateBaseRequestMessage(xmlMap, imageRequestMessage);
                imageRequestMessage.setMsgId(Long.parseLong(xmlMap.get("MsgId")));
                imageRequestMessage.setMediaId(mediaId);
                imageRequestMessage.setPicUrl(picUrl);
                return imageRequestMessage;
            //链接消息
            case LINK_MESSAGE:
                String title = xmlMap.get("Title");
                String description = xmlMap.get("Description");
                String url = xmlMap.get("Url");
                LinkRequestMessage linkRequestMessage = new LinkRequestMessage();
                MessageUtils.inflateBaseRequestMessage(xmlMap, linkRequestMessage);
                linkRequestMessage.setMsgId(Long.parseLong(xmlMap.get("MsgId")));
                linkRequestMessage.setDescription(description);
                linkRequestMessage.setTitle(title);
                linkRequestMessage.setUrl(url);
                return linkRequestMessage;
            //语音消息
            case VOICE_MESSAGE:
                VoiceRequestMessage voiceRequestMessage = new VoiceRequestMessage();
                MessageUtils.inflateBaseRequestMessage(xmlMap, voiceRequestMessage);
                voiceRequestMessage.setMsgId(Long.parseLong(xmlMap.get("MsgId")));
                voiceRequestMessage.setMediaId(xmlMap.get("MediaId"));
                voiceRequestMessage.setFormat(xmlMap.get("Format"));
                return voiceRequestMessage;
            //视频、短视频消息
            case VIDEO_MESSAGE:
            case SHORTVIDEO_MESSAGE:
                VideoRequestMessage videoRequestMessage = new VideoRequestMessage();
                MessageUtils.inflateBaseRequestMessage(xmlMap, videoRequestMessage);
                videoRequestMessage.setMsgId(Long.parseLong(xmlMap.get("MsgId")));
                videoRequestMessage.setMediaId(xmlMap.get("MediaId"));
                videoRequestMessage.setThumbMediaId(xmlMap.get("ThumbMediaId"));
                return videoRequestMessage;
            //地理位置消息
            case LOCATION_MESSAGE:
                String location_x = xmlMap.get("Location_X");
                String location_y = xmlMap.get("Location_Y");
                String scale = xmlMap.get("Scale");
                String label = xmlMap.get("Label");
                LocationRequestMessage locationRequestMessage = new LocationRequestMessage();
                MessageUtils.inflateBaseRequestMessage(xmlMap, locationRequestMessage);
                locationRequestMessage.setMsgId(Long.parseLong(xmlMap.get("MsgId")));
                locationRequestMessage.setLocation_X(location_x);
                locationRequestMessage.setLocation_Y(location_y);
                locationRequestMessage.setLabel(label);
                locationRequestMessage.setScale(scale);
                return locationRequestMessage;
            //事件消息
            case EVENT:
                String event = xmlMap.get("Event");
                EventType eventType = EventType.valueBy(event);
                Preconditions.checkNotNull(eventType, "no event message messageType found!");
                switch (eventType){
                    //关注、取消关注消息
                    case EVENT_SUBSCRIBE:
                    case EVENT_UNSUBSCRIBE:
                        SubOrUnSubEventRequestMessage subOrUnSubEventRequestMessage = new SubOrUnSubEventRequestMessage();
                        MessageUtils.inflateBaseRequestMessage(xmlMap,subOrUnSubEventRequestMessage);
                        subOrUnSubEventRequestMessage.setEvent(event);
                        return subOrUnSubEventRequestMessage;
                    //扫描二维码时未关注公众号消息
                    case EVENT_SCAN_SUBSCRIBE:
                        //扫描二维码时已关注公众号消息
                    case EVENT_SCAN:
                        ScanQrWithParameterEventRequestMessage scanQrWithParameterEventRequestMessage = new ScanQrWithParameterEventRequestMessage();
                        MessageUtils.inflateBaseRequestMessage(xmlMap,scanQrWithParameterEventRequestMessage);
                        scanQrWithParameterEventRequestMessage.setEvent(event);
                        scanQrWithParameterEventRequestMessage.setEventKey(xmlMap.get("EventKey"));
                        scanQrWithParameterEventRequestMessage.setTicket(xmlMap.get("Ticket"));
                        return scanQrWithParameterEventRequestMessage;
                    //上报地理位置事件消息
                    case EVENT_UPLOAD_LOCATION:
                        UploadLocationEventRequestMessage uploadLocationEventRequestMessage = new UploadLocationEventRequestMessage();
                        MessageUtils.inflateBaseRequestMessage(xmlMap,uploadLocationEventRequestMessage);
                        uploadLocationEventRequestMessage.setEvent(event);
                        uploadLocationEventRequestMessage.setLatitude(xmlMap.get("Latitude"));
                        uploadLocationEventRequestMessage.setLongitude(xmlMap.get("Longitude"));
                        uploadLocationEventRequestMessage.setPrecision(xmlMap.get("Precision"));
                        return uploadLocationEventRequestMessage;
                    //点击菜单拉取消息时的事件推送
                    case EVENT_CUSTOM_MENU_CLICK:
                        //点击菜单跳转链接时的事件推送
                    case EVENT_CUSTOM_MENU_VIEW:
                        CustomMenuClickOrViewEventRequestMessage customMenuClickOrViewEventRequestMessage = new CustomMenuClickOrViewEventRequestMessage();
                        MessageUtils.inflateBaseRequestMessage(xmlMap,customMenuClickOrViewEventRequestMessage);
                        customMenuClickOrViewEventRequestMessage.setEvent(event);
                        customMenuClickOrViewEventRequestMessage.setEventKey(xmlMap.get("EventKey"));
                        return customMenuClickOrViewEventRequestMessage;
                }
            default:
                LOGGER.warn("there is no definded message messageType {}.", messageType.getTypeStr());

        }
        return null;
    }


}
