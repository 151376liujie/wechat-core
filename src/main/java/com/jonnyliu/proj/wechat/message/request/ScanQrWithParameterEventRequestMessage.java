package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.EventType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;

/**
 * 扫描二维码事件的消息封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("xml")
@ToString(callSuper = true)
public class ScanQrWithParameterEventRequestMessage extends EventRequestMessage {

    /**
     * (用户未关注时)事件KEY值，qrscene_为前缀，后面为二维码的参数值 <br/>
     * (用户已关注时)事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     */
    @XStreamAlias("EventKey")
    private String eventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    @XStreamAlias("Ticket")
    private String ticket;

    @Override
    public String getEvent() {
        return EventType.EVENT_SCAN.getTypeStr();
    }
}
