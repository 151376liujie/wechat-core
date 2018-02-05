package com.jonnyliu.proj.wechat.message.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * 公众号  ---->  用户
 * 对响应消息的基类封装,各个类型的回复消息可继承该类实现扩展
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class BaseResponseMessage implements Serializable {

    /**
     * 接收方帐号（收到的OpenID）
     */
    @XStreamAlias("ToUserName")
    private String toUserName;

    /**
     * 开发者微信号
     */
    @XStreamAlias("FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    @XStreamAlias("CreateTime")
    private long createTime;

    /**
     * 消息类型
     */
    @XStreamAlias("MsgType")
    private String msgType;

}
