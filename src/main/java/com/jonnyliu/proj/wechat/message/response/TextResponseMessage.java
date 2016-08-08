package com.jonnyliu.proj.wechat.message.response;

import com.jonnyliu.proj.wechat.enums.MessageType;

/**
 * 回复文本消息的封装
 * Created by jonnyliu-ds8 on 2016/8/5.
 */
public class TextResponseMessage extends BaseResponseMessage {

    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public void setMsgType(String msgType) {
        super.setMsgType(MessageType.TEXT_MESSAGE.getTypeStr());
    }

    @Override
    public String getMsgType() {
        return MessageType.TEXT_MESSAGE.getTypeStr();
    }
}
