package com.jonnyliu.proj.wechat.message.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户 ----->  公众号
 * 封装用户发送给公众号的消息,各个类型的消息可继承该类实现扩展
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class BaseRequestMessage implements Serializable{

    /**
     * 开发者微信号
     */
    private String ToUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;

    /**
     * 消息创建时间 （整型）
     */
    private long CreateTime;

    /**
     * 消息类型
     */
    private String MsgType;

}
