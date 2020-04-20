package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.EventType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;

/**
 * 自定义菜单点击事件消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("xml")
@ToString(callSuper = true)
public class CustomMenuClickEventRequestMessage extends EventRequestMessage {

    /**
     * 值为VIEW时EventKey值为设置的跳转URL
     * 值为CLICK时,事件KEY值与自定义菜单接口中KEY值对应
     */
    @XStreamAlias("EventKey")
    protected String eventKey;

    @Override
    public String getEvent() {
        return EventType.EVENT_CUSTOM_MENU_CLICK.getTypeStr();
    }
}
