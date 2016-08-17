package com.jonnyliu.proj.wechat.enums;

/**
 * 事件消息类型
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-17 15:10.
 */
public enum EventType {

    /**
     * 关注事件消息
     */
    EVENT_SUBSCRIBE("subscribe"),

    /**
     * 取消事件消息
     */
    EVENT_UNSUBSCRIBE("unsubscribe"),

    /**
     * 上报地理位置事件
     */
    EVENT_UPLOAD_LOCATION("location"),

    /**
     * 自定义菜单事件(点击菜单事件)
     */
    EVENT_CUSTOM_MENU_CLICK("click"),

    /**
     * 自定义菜单事件(点击菜单跳转链接时的事件)
     */
    EVENT_CUSTOM_MENU_VIEW("view"),

    /**
     * 用户未关注时扫描二维码事件
     */
    EVENT_SCAN_SUBSCRIBE("subscribe"),

    /**
     * 用户已关注时扫描二维码事件
     */
    EVENT_SCAN("scan");


    private String typeStr;

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    EventType(String typeStr) {
        this.typeStr = typeStr;
    }

    public static EventType valueBy(String typeStr) {
        for (EventType eventType : EventType.values()) {
            if (eventType.getTypeStr().equalsIgnoreCase(typeStr)) {
                return eventType;
            }
        }
        return null;
    }

}
