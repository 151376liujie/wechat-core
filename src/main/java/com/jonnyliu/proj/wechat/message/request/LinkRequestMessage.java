package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.MessageType;
import lombok.Data;

/**
 * Created by jonnyliu-ds8 on 2016/8/5.
 */
@Data
public class LinkRequestMessage extends CommonRequestMessage {

    /**
     * 消息标题
     */
    private String Title;

    /**
     * 消息描述
     */
    private String Description;

    /**
     * 消息链接
     */
    private String Url;

    @Override
    public String getMsgType() {
        return MessageType.LINK_MESSAGE.getTypeStr();
    }

}
