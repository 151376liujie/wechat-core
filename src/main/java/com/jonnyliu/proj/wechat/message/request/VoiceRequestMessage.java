package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;

/**
 * 语音消息的封装
 * Created by jonnyliu-ds8 on 2016/8/5.
 */
public class VoiceRequestMessage extends BaseRequestMessage {


    /**
     *消息id，64位整型
     */
    private long MsgId;
    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;

    /**
     * 语音格式，如amr，speex等
     */
    private String Format;

    /**
     * 语音识别结果，UTF8编码
     */
    private String Recognition;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        this.Recognition = recognition;
    }

    @Override
    public String getMsgType() {
        return MessageType.VOICE_MESSAGE.getTypeStr();
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        this.MsgId = msgId;
    }
}
