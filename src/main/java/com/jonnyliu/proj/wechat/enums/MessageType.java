package com.jonnyliu.proj.wechat.enums;

/**
 * 消息类型的枚举定义 <br/>
 * author : 980463316@qq.com <br/>
 * Created on 2016/8/5 15:13.
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
    SHORT_VIDEO_MESSAGE("shortvideo"),
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
     * 事件消息
     */
    EVENT("event");


    private String typeStr;

    public String getTypeStr() {
        return this.typeStr;
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
