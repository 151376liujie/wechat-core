package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;

/**
 * 文本消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("xml")
@ToString(callSuper = true)
public class TextRequestMessage extends CommonRequestMessage {

    /**
     * 消息内容
     */
    @XStreamAlias("Content")
    private String content;

    @Override
    public String getMsgType() {
        return MessageType.TEXT_MESSAGE.getTypeStr();
    }

}
