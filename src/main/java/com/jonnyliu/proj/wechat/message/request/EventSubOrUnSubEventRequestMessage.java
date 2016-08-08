package com.jonnyliu.proj.wechat.message.request;

/**
 * 订阅(取消订阅)的消息封装
 * Created by liujie-ds8 on 2016/8/5.
 */
public class EventSubOrUnSubEventRequestMessage extends BaseRequestMessage {

    /**
     * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
     */
    private String Event;

}
