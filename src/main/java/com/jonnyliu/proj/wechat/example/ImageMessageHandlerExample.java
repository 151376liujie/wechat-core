package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.ImageRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 视频消息接收和响应的code example，
 * 实现回复相同的图片给用户
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 11:42.
 */
@Component
@MessageWorker(messageType = MessageType.IMAGE_MESSAGE)
public class ImageMessageHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        //在这里实现你自己的业务逻辑
        ImageRequestMessage imageRequestMessage = (ImageRequestMessage) baseRequestMessage;
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("MediaId",imageRequestMessage.getMediaId());
        return MessageUtils.buildImageResponseMessage(baseRequestMessage,paramMap);
    }
}
