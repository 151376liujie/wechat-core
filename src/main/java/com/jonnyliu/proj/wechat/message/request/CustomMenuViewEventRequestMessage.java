package com.jonnyliu.proj.wechat.message.request;

import com.jonnyliu.proj.wechat.enums.EventType;
import lombok.Data;

/**
 * 自定义菜单跳转事件消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class CustomMenuViewEventRequestMessage extends CustomMenuClickEventRequestMessage {

    /**
     * 值为VIEW时EventKey值为设置的跳转URL
     */
    protected String EventKey;

    @Override
    public String getEvent() {
        return EventType.EVENT_CUSTOM_MENU_VIEW.getTypeStr();
    }
}
