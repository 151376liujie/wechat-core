package com.jonnyliu.proj.wechat.message.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.ToString;

/**
 * 普通类型消息基类
 * Author: jonny
 * Time: 2018-02-03 23:22.
 */
@ToString(callSuper = true)
public class CommonRequestMessage extends BaseRequestMessage {

    /**
     * 消息id，64位整型
     */
    @XStreamAlias("MsgId")
    private Long msgId;
}
