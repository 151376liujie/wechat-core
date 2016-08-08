package com.jonnyliu.proj.wechat.enums;

/**
 * 消息类型
 *
 * Created by liujie-ds8 on 2016/8/5.
 */
public enum MessageType {

    /**
     * 文本消息
     */
    TEXT_MESSAGE("text"),
    /**
     * 图片消息
     */
    IMAGE_MESSAGE("image"),
    /**
     * 语音消息
     */
    VOICE_MESSAGE("voice"),
    /**
     * 视频消息
     */
    VIDEO_MESSAGE("video"),
    /**
     * 短视频消息
     */
    SHORTVIDEO_MESSAGE("shortvideo"),
    /**
     * 位置消息
     */
    LOCATION_MESSAGE("location"),
    /**
     * 链接消息
     */
    LINK_MESSAGE("link"),

    /**
     * 音乐消息
     */
    MUSIC_MESSAGE("music"),

    /**
     * 图文消息
     */
    NEWS_MESSAGE("news"),

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
    EVENT_LOCATION("location"),

    /**
     * 自定义菜单事件(点击菜单事件)
     */
    EVENT_CLICK("click"),

    /**
     * 自定义菜单事件(点击菜单跳转链接时的事件)
     */
    EVENT_VIEW("view"),

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

    MessageType(String typeStr) {
        this.typeStr = typeStr;
    }


    public static MessageType valueBy(String typeStr) {
        for (MessageType messageType : MessageType.values()) {
            if (messageType.getTypeStr().equalsIgnoreCase(typeStr)) {
                return messageType;
            }
        }
        return null;
    }
}
