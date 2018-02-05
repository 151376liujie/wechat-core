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
    EVENT_SCAN("scan"),
    /**
     * 扫码带提示事件类型
     */
    EVENT_SCAN_CODE_WAIT_MSG("scancode_waitmsg"),

    EVENT_SCAN_CODE_PUSH("scancode_push"),

    /**
     * 发送位置菜单事件
     */
    EVENT_LOCATION_SELECT("location_select"),
    /**
     * 系统拍照发图事件推送
     */
    EVENT_PIC_SYS_PHOTO("pic_sysphoto"),
    /**
     * 拍照或相册发图事件推送
     */
    EVENT_PIC_PHOTO_OR_ALBUM("pic_photo_or_album"),
    /**
     * 微信相册发图事件推送
     */
    EVENT_PIC_WEIXIN("pic_weixin"),


    /**
     * 无意义的一个值，仅仅用于在注解中的一个默认值
     */
    NULL("null");


    private String typeStr;

    public String getTypeStr() {
        return this.typeStr;
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
