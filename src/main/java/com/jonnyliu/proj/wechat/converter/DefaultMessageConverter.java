package com.jonnyliu.proj.wechat.converter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.message.request.*;
import org.apache.commons.beanutils.BeanUtils;
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
    public BaseRequestMessage doConvert(Map<String, String> xmlMap) throws Exception {

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("prepare to convert xml message : {}", xmlMap);
        }

        MessageType messageType = MessageType.valueBy(xmlMap.get("MsgType"));
        Preconditions.checkNotNull(messageType, "no MsgType found!");

        //将xmlMap中的key的首字母小写,并重新放入新的Map中,之所以要转换key的首字母大小写,因为beanutils无法通过反射找到对应set方法
        Map<String, Object> convertedMap = Maps.newHashMap();
        xmlMap.forEach((key, value) -> {
            char[] charArray = key.toCharArray();
            charArray[0] = Character.toLowerCase(charArray[0]);
            convertedMap.put(String.valueOf(charArray), value);
        });

        switch (messageType) {
            //文本消息
            case TEXT_MESSAGE:
                TextRequestMessage textRequestMessage = new TextRequestMessage();
                BeanUtils.populate(textRequestMessage, convertedMap);
                return textRequestMessage;
            //图片消息
            case IMAGE_MESSAGE:
                ImageRequestMessage imageRequestMessage = new ImageRequestMessage();
                BeanUtils.populate(imageRequestMessage, convertedMap);
                return imageRequestMessage;
            //链接消息
            case LINK_MESSAGE:
                LinkRequestMessage linkRequestMessage = new LinkRequestMessage();
                BeanUtils.populate(linkRequestMessage, convertedMap);
                return linkRequestMessage;
            //语音消息
            case VOICE_MESSAGE:
                VoiceRequestMessage voiceRequestMessage = new VoiceRequestMessage();
                BeanUtils.populate(voiceRequestMessage, convertedMap);
                return voiceRequestMessage;
            //视频、短视频消息
            case VIDEO_MESSAGE:
            case SHORTVIDEO_MESSAGE:
                VideoRequestMessage videoRequestMessage = new VideoRequestMessage();
                BeanUtils.populate(videoRequestMessage, convertedMap);
                return videoRequestMessage;
            //地理位置消息
            case LOCATION_MESSAGE:
                LocationRequestMessage locationRequestMessage = new LocationRequestMessage();
                BeanUtils.populate(locationRequestMessage, convertedMap);
                return locationRequestMessage;
            //事件消息
            case EVENT:
                String event = xmlMap.get("Event");
                EventType eventType = EventType.valueBy(event);
                Preconditions.checkNotNull(eventType, "no event message messageType found!");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("convert to {} message object!", eventType.getTypeStr());
                }
                switch (eventType) {
                    //关注、取消关注消息
                    case EVENT_SUBSCRIBE:
                    case EVENT_UNSUBSCRIBE:
                        SubOrUnSubEventRequestMessage subOrUnSubEventRequestMessage = new SubOrUnSubEventRequestMessage();
                        BeanUtils.populate(subOrUnSubEventRequestMessage, convertedMap);
                        return subOrUnSubEventRequestMessage;
                    //扫描二维码时未关注公众号消息
                    case EVENT_SCAN_SUBSCRIBE:
                        //扫描二维码时已关注公众号消息
                    case EVENT_SCAN:
                        ScanQrWithParameterEventRequestMessage scanQrWithParameterEventRequestMessage = new ScanQrWithParameterEventRequestMessage();
                        BeanUtils.populate(scanQrWithParameterEventRequestMessage, convertedMap);
                        return scanQrWithParameterEventRequestMessage;
                    //上报地理位置事件消息
                    case EVENT_UPLOAD_LOCATION:
                        UploadLocationEventRequestMessage uploadLocationEventRequestMessage = new UploadLocationEventRequestMessage();
                        BeanUtils.populate(uploadLocationEventRequestMessage, convertedMap);
                        return uploadLocationEventRequestMessage;
                    //点击菜单拉取消息时的事件推送
                    case EVENT_CUSTOM_MENU_CLICK:
                        //点击菜单跳转链接时的事件推送
                    case EVENT_CUSTOM_MENU_VIEW:
                        CustomMenuClickOrViewEventRequestMessage customMenuClickOrViewEventRequestMessage = new CustomMenuClickOrViewEventRequestMessage();
                        BeanUtils.populate(customMenuClickOrViewEventRequestMessage, convertedMap);
                        return customMenuClickOrViewEventRequestMessage;
                    case EVENT_LOCATION_SELECT:
                        LocationSelectMenuEventRequestMessage locationSelectMenuEventRequestMessage = new LocationSelectMenuEventRequestMessage();
                        BeanUtils.populate(locationSelectMenuEventRequestMessage, convertedMap);
                        return locationSelectMenuEventRequestMessage;
                }
            default:
                LOGGER.warn("there is no defined message messageType {}.", messageType.getTypeStr());
        }
        return null;
    }

}
