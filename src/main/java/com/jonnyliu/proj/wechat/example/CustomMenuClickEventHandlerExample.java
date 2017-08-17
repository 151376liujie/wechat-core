package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.bean.GetUserInfoParameter;
import com.jonnyliu.proj.wechat.bean.WechatUser;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.Lang;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.CustomMenuClickOrViewEventRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.service.user.WechatUserService;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义菜单跳转事件示例代码
 * author:980463316@qq.com
 * Created on 2016-09-07 23:37.
 */
@Component
@MessageProcessor(messageType = MessageType.EVENT, eventType = EventType.EVENT_CUSTOM_MENU_CLICK)
public class CustomMenuClickEventHandlerExample extends AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomMenuClickEventHandlerExample.class);

    @Autowired
    private WechatUserService wechatUserService;

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {

        CustomMenuClickOrViewEventRequestMessage customMenuClickOrViewEventRequestMessage = (CustomMenuClickOrViewEventRequestMessage) baseRequestMessage;

        String eventKey = customMenuClickOrViewEventRequestMessage.getEventKey();

        if (WechatConstant.MENU_MY_CLICK_KEY.equalsIgnoreCase(eventKey)) {
            //用户点击了"我的信息"按钮
            WechatUser userInfo = wechatUserService.getWechatUserInfo(new GetUserInfoParameter(customMenuClickOrViewEventRequestMessage.getFromUserName(), Lang.CHINESE.getLanguageCode()));
            if (userInfo == null) {
                return MessageUtils.buildTextResponseMessage(baseRequestMessage, "抱歉,没有获取到您的信息,请您稍后再重试.");
            }
            String userInfoTemplate = "您的信息如下:\n☕openid:%s\n☕用户昵称:%s\n☕性别:%s\n☕所在国家:%s\n☕所在省份\n☕所在城市:%s";
            String userInfoString = String.format(userInfoTemplate, userInfo.getOpenid(), userInfo.getNickname(), userInfo.getSexString(), userInfo.getCountry(), userInfo.getProvince(), userInfo.getCity());
            return MessageUtils.buildTextResponseMessage(baseRequestMessage, userInfoString);
        }

        return null;

    }
}
