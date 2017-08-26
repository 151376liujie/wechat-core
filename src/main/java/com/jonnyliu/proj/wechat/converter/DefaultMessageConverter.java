package com.jonnyliu.proj.wechat.converter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.message.request.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 默认的消息转换器，将用户发送至公众号的消息xml字符串转换为消息对象
 * author : 980463316@qq.com <br/>
 * Created on 2016/8/6 15:31.
 */
@Slf4j
@Component
public class DefaultMessageConverter implements MessageConvert {

    @Override
    public BaseRequestMessage doConvert(Map<String, String> xmlMap) throws Exception {

        if (log.isInfoEnabled()) {
            log.info("prepare to convert xml message : {}", xmlMap);
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
                if (log.isInfoEnabled()) {
                    log.info("converted message: {}", textRequestMessage);
                }
                return textRequestMessage;
            //图片消息
            case IMAGE_MESSAGE:
                ImageRequestMessage imageRequestMessage = new ImageRequestMessage();
                BeanUtils.populate(imageRequestMessage, convertedMap);
                if (log.isInfoEnabled()) {
                    log.info("converted message: {}", imageRequestMessage);
                }
                return imageRequestMessage;
            //链接消息
            case LINK_MESSAGE:
                LinkRequestMessage linkRequestMessage = new LinkRequestMessage();
                BeanUtils.populate(linkRequestMessage, convertedMap);
                if (log.isInfoEnabled()) {
                    log.info("converted message: {}", linkRequestMessage);
                }
                return linkRequestMessage;
            //语音消息
            case VOICE_MESSAGE:
                VoiceRequestMessage voiceRequestMessage = new VoiceRequestMessage();
                BeanUtils.populate(voiceRequestMessage, convertedMap);
                if (log.isInfoEnabled()) {
                    log.info("converted message: {}", voiceRequestMessage);
                }
                return voiceRequestMessage;
            //视频、短视频消息
            case VIDEO_MESSAGE:
            case SHORTVIDEO_MESSAGE:
                VideoRequestMessage videoRequestMessage = new VideoRequestMessage();
                BeanUtils.populate(videoRequestMessage, convertedMap);
                if (log.isInfoEnabled()) {
                    log.info("converted message: {}", videoRequestMessage);
                }
                return videoRequestMessage;
            //地理位置消息
            case LOCATION_MESSAGE:
                LocationRequestMessage locationRequestMessage = new LocationRequestMessage();
                BeanUtils.populate(locationRequestMessage, convertedMap);
                if (log.isInfoEnabled()) {
                    log.info("converted message: {}", locationRequestMessage);
                }
                return locationRequestMessage;
            //事件消息
            case EVENT:
                String event = xmlMap.get("Event");
                EventType eventType = EventType.valueBy(event);
                Preconditions.checkNotNull(eventType, "no event message messageType found!");
                if (log.isDebugEnabled()) {
                    log.debug("convert to {} message object!", eventType.getTypeStr());
                }
                switch (eventType) {
                    //关注、取消关注消息
                    case EVENT_SUBSCRIBE:
                    case EVENT_UNSUBSCRIBE:
                        SubOrUnSubEventRequestMessage subOrUnSubEventRequestMessage = new SubOrUnSubEventRequestMessage();
                        BeanUtils.populate(subOrUnSubEventRequestMessage, convertedMap);
                        if (log.isInfoEnabled()) {
                            log.info("converted message: {}", subOrUnSubEventRequestMessage);
                        }
                        return subOrUnSubEventRequestMessage;
                    //扫描二维码时未关注公众号消息
                    case EVENT_SCAN_SUBSCRIBE:
                        //扫描二维码时已关注公众号消息
                    case EVENT_SCAN:
                        ScanQrWithParameterEventRequestMessage scanQrWithParameterEventRequestMessage = new ScanQrWithParameterEventRequestMessage();
                        BeanUtils.populate(scanQrWithParameterEventRequestMessage, convertedMap);
                        if (log.isInfoEnabled()) {
                            log.info("converted message: {}", scanQrWithParameterEventRequestMessage);
                        }
                        return scanQrWithParameterEventRequestMessage;
                    case EVENT_SCAN_CODE_WAIT_MSG:
                        ScanCodeEventRequestMessage scanCodeEventRequestMessage = new ScanCodeEventRequestMessage();
                        BeanUtils.populate(scanCodeEventRequestMessage, convertedMap);
                        ScanCodeInfo scanCodeInfo = new ScanCodeInfo();
                        BeanUtils.populate(scanCodeInfo, convertedMap);
                        scanCodeEventRequestMessage.setScanCodeInfo(scanCodeInfo);
                        if (log.isInfoEnabled()) {
                            log.info("converted message: {}", scanCodeEventRequestMessage);
                        }
                        return scanCodeEventRequestMessage;
                    //上报地理位置事件消息
                    case EVENT_UPLOAD_LOCATION:
                        UploadLocationEventRequestMessage uploadLocationEventRequestMessage = new UploadLocationEventRequestMessage();
                        BeanUtils.populate(uploadLocationEventRequestMessage, convertedMap);
                        if (log.isInfoEnabled()) {
                            log.info("converted message: {}", uploadLocationEventRequestMessage);
                        }
                        return uploadLocationEventRequestMessage;
                    //点击菜单拉取消息时的事件推送
                    case EVENT_CUSTOM_MENU_CLICK:
                        CustomMenuClickEventRequestMessage customMenuClickEventRequestMessage = new CustomMenuClickEventRequestMessage();
                        BeanUtils.populate(customMenuClickEventRequestMessage, convertedMap);
                        if (log.isInfoEnabled()) {
                            log.info("converted message: {}", customMenuClickEventRequestMessage);
                        }
                        return customMenuClickEventRequestMessage;
                    //点击菜单跳转链接时的事件推送
                    case EVENT_CUSTOM_MENU_VIEW:
                        CustomMenuViewEventRequestMessage menuViewEventRequestMessage = new CustomMenuViewEventRequestMessage();
                        BeanUtils.populate(menuViewEventRequestMessage, convertedMap);
                        if (log.isInfoEnabled()) {
                            log.info("converted message: {}", menuViewEventRequestMessage);
                        }
                        return menuViewEventRequestMessage;
                    case EVENT_LOCATION_SELECT:
                        LocationSelectMenuEventRequestMessage locationSelectMenuEventRequestMessage = new LocationSelectMenuEventRequestMessage();
                        BeanUtils.populate(locationSelectMenuEventRequestMessage, convertedMap);
                        SendLocationInfo sendLocationInfo = new SendLocationInfo();
                        BeanUtils.populate(sendLocationInfo, convertedMap);
                        locationSelectMenuEventRequestMessage.setSendLocationInfo(sendLocationInfo);
                        if (log.isInfoEnabled()) {
                            log.info("converted message: {}", locationSelectMenuEventRequestMessage);
                        }
                        return locationSelectMenuEventRequestMessage;
                    case EVENT_PIC_SYS_PHOTO:
                    case EVENT_PIC_PHOTO_OR_ALBUM:
                    case EVENT_PIC_WEIXIN:
                        PhotoMenuEventRequestMessage photoMenuEventRequestMessage = new PhotoMenuEventRequestMessage();
                        BeanUtils.populate(photoMenuEventRequestMessage, convertedMap);
                        int count = Integer.parseInt(xmlMap.get("Count"));
                        String picMd5Sum = xmlMap.get("PicMd5Sum");
                        photoMenuEventRequestMessage.getSendPicsInfo().setCount(count);
                        photoMenuEventRequestMessage.getSendPicsInfo().setPicMd5Sum(picMd5Sum);
                        if (log.isInfoEnabled()) {
                            log.info("converted message: {}", photoMenuEventRequestMessage);
                        }
                        return photoMenuEventRequestMessage;
                }
            default:
                log.warn("there is no defined message messageType {}.", messageType.getTypeStr());
        }
        return null;
    }

}
