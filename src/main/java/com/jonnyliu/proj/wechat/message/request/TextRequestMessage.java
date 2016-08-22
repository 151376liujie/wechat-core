package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;

/**
 * 文本消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
public class TextRequestMessage extends BaseRequestMessage {

    /**
     *消息id，64位整型
     */
    private long MsgId;

    /**
     * 消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String getMsgType() {
        return MessageType.TEXT_MESSAGE.getTypeStr();
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

    public long getMsgId() {
        return MsgId;
    }
}
