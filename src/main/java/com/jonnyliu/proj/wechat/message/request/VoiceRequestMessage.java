package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;
import lombok.Data;

/**
 * 语音消息的封装
 * Created by jonnyliu-ds8 on 2016/8/5.
 */
@Data
public class VoiceRequestMessage extends CommonRequestMessage {

    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体
     */
    private String MediaID;

    /**
     * 语音格式，如amr，speex等
     */
    private String Format;

    /**
     * 语音识别结果，UTF8编码
     */
    private String Recognition;

    @Override
    public String getMsgType() {
        return MessageType.VOICE_MESSAGE.getTypeStr();
    }
}
