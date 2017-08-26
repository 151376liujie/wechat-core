package com.jonnyliu.proj.wechat.message.request;

import lombok.Data;

/**
 * 自定义菜单跳转事件消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
public class CustomMenuViewEventRequestMessage extends CustomMenuClickEventRequestMessage {

    /**
     * 菜单ID,如果是个性化菜单,则可以通过这个字段,知道是哪个规则的菜单被点击了
     */
    private String MenuId;

}
