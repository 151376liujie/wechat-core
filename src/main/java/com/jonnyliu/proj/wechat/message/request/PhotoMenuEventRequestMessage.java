package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.EventType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 系统拍照发图菜单事件
 * Author: jonny
 * Time: 2017-08-17 11:36.
 */
@Data
@XStreamAlias("xml")
public class PhotoMenuEventRequestMessage extends EventRequestMessage {

    @XStreamAlias("EventKey")
    private String eventKey;

    /**
     * 发送的图片信息
     */
    @XStreamAlias("SendPicsInfo")
    private SendPicsInfo sendPicsInfo = new SendPicsInfo();


    @Override
    public String getEvent() {
        return EventType.EVENT_PIC_WEIXIN.getTypeStr();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PhotoMenuEventRequestMessage{");
        sb.append("SendPicsInfo=").append(this.sendPicsInfo);
        sb.append(", eventKey='").append(this.eventKey).append('\'');
        sb.append(", toUserName='").append(this.toUserName).append('\'');
        sb.append(", fromUserName='").append(this.fromUserName).append('\'');
        sb.append(", createTime=").append(this.createTime);
        sb.append(", msgType='").append(this.msgType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
