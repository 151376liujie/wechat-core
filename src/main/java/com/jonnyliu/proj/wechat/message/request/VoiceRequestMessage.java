package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;

/**
 * 语音消息的封装
 * Created by jonnyliu-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("xml")
@ToString(callSuper = true)
public class VoiceRequestMessage extends CommonRequestMessage {

    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体
     */
    @XStreamAlias("MediaId")
    private String mediaId;

    /**
     * 语音格式，如amr，speex等
     */
    @XStreamAlias("Format")
    private String format;

    /**
     * 语音识别结果，UTF8编码
     */
    @XStreamAlias("Recognition")
    private String recognition;

    @Override
    public String getMsgType() {
        return MessageType.VOICE_MESSAGE.getTypeStr();
    }
}
