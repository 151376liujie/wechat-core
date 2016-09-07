package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.VoiceRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 语音消息接收和响应的code example
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-19 11:42.
 */
@Component
@MessageWorker(messageType = MessageType.VOICE_MESSAGE)
public class VoiceMessageHandlerExample extends AbstractMessageHandler {
    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        //在这里实现你自己的业务逻辑
        VoiceRequestMessage voiceRequestMessage = (VoiceRequestMessage) baseRequestMessage;
        String recognition = voiceRequestMessage.getRecognition();
        String format = voiceRequestMessage.getFormat();
        String mediaId = voiceRequestMessage.getMediaId();

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("Recognition",recognition);
        paramMap.put("Format",format);
        paramMap.put("MediaId",mediaId);
        return MessageUtils.buildVoiceResponseMessage(baseRequestMessage,paramMap);
    }
}
