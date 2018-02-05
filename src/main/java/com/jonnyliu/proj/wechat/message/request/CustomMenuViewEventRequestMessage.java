package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.EventType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 自定义菜单跳转事件消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("xml")
public class CustomMenuViewEventRequestMessage extends EventRequestMessage {

    /**
     * 值为VIEW时EventKey值为设置的跳转URL
     */
    @XStreamAlias("EventKey")
    protected String eventKey;

    @XStreamAlias("MenuId")
    private Long menuId;

    @Override
    public String getEvent() {
        return EventType.EVENT_CUSTOM_MENU_VIEW.getTypeStr();
    }
}
