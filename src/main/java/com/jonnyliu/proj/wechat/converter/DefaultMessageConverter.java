package com.jonnyliu.proj.wechat.converter;

import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.ImageRequestMessage;
import com.jonnyliu.proj.wechat.message.request.TextRequestMessage;
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

        switch (messageType) {
            case TEXT_MESSAGE:
                String content = xmlMap.get("Content");
                TextRequestMessage textRequestMessage = new TextRequestMessage();
                //填充消息对象
                MessageUtils.inflateBaseRequestMessage(xmlMap, textRequestMessage);
                textRequestMessage.setContent(content);
                return textRequestMessage;
            case IMAGE_MESSAGE:
                String mediaId = xmlMap.get("MediaId");
                String picUrl = xmlMap.get("PicUrl");
                ImageRequestMessage imageRequestMessage = new ImageRequestMessage();
                MessageUtils.inflateBaseRequestMessage(xmlMap, imageRequestMessage);
                imageRequestMessage.setMediaId(mediaId);
                imageRequestMessage.setPicUrl(picUrl);
                return imageRequestMessage;
            //TODO:其他类的转换
        }
        return null;
    }


}
