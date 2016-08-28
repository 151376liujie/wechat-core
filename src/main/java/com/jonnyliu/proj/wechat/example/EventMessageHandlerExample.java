package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.bean.GetUserInfoParam;
import com.jonnyliu.proj.wechat.bean.WechatUser;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.Lang;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.*;
import com.jonnyliu.proj.wechat.message.response.Article;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.service.user.WechatUserService;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件消息处理器示例
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-22 13:47.
 */
@MessageWorker(type = MessageType.EVENT)
public class EventMessageHandlerExample extends AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventMessageHandlerExample.class);

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        //（取消）关注事件消息
        if (baseRequestMessage instanceof SubOrUnSubEventRequestMessage) {

            SubOrUnSubEventRequestMessage subOrUnSubEventRequestMessage = (SubOrUnSubEventRequestMessage) baseRequestMessage;
            String event = subOrUnSubEventRequestMessage.getEvent();
            EventType eventType = EventType.valueBy(event);
            //关注事件
            String fromUserName = subOrUnSubEventRequestMessage.getFromUserName();
            if (eventType == EventType.EVENT_SUBSCRIBE) {
                WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
                WechatUserService wechatUserService = webApplicationContext.getBean(WechatUserService.class);
                WechatUser wechatUserInfo = wechatUserService.getWechatUserInfo(new GetUserInfoParam(fromUserName, Lang.CHINESE.getLanguageCode()));
                String title = "你好！感谢您的关注！";
                if (wechatUserInfo == null) {
                    return MessageUtils.buildTextResponseMessage(baseRequestMessage, title);
                }
                title = "亲爱的:" + wechatUserInfo.getNickname() + "," + title;
                Article article = new Article();
                article.setUrl("https://github.com/151376liujie/wechat-core");
                article.setTitle(title);
                article.setPicUrl(wechatUserInfo.getHeadimgurl());
                article.setDescription("微信开发框架，封装了微信消息发送和接收的细节，用户只需关注自己业务本身，并且支持注解开发。");
                List<Article> list = new ArrayList<>(1);
                list.add(article);
                Map<String, String> param = new HashMap<>();
                param.put("ArticleCount", String.valueOf(list.size()));
                return MessageUtils.buildNewsResponseMessage(baseRequestMessage, param, list);
            } else if (eventType == EventType.EVENT_UNSUBSCRIBE) {//取消关注事件
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("用户：[{}] 取消了对公众号的关注！", fromUserName);
                }
            }
        } else if (baseRequestMessage instanceof UploadLocationEventRequestMessage) {
            //用户上传地理位置信息事件类型
            UploadLocationEventRequestMessage uploadLocationEventRequestMessage = (UploadLocationEventRequestMessage) baseRequestMessage;
            //在这里实现你自己的业务逻辑

        } else if (baseRequestMessage instanceof ScanQrWithParameterEventRequestMessage) {
            //扫描二维码事件
            ScanQrWithParameterEventRequestMessage scanQrWithParameterEventRequestMessage = (ScanQrWithParameterEventRequestMessage) baseRequestMessage;
            String event = scanQrWithParameterEventRequestMessage.getEvent();
            EventType eventType = EventType.valueBy(event);
            //扫描时未关注公众号的事件
            if (eventType == EventType.EVENT_SCAN_SUBSCRIBE) {
                LOGGER.info("扫描时未关注公众号:" + scanQrWithParameterEventRequestMessage.toString());
            } else if (eventType == EventType.EVENT_SCAN) {//带参数的二维码扫描事件类型
                LOGGER.info("带参数的二维码扫描:" + scanQrWithParameterEventRequestMessage.toString());
            }
        } else if (baseRequestMessage instanceof CustomMenuClickOrViewEventRequestMessage) {
            CustomMenuClickOrViewEventRequestMessage customMenuClickOrViewEventRequestMessage = (CustomMenuClickOrViewEventRequestMessage) baseRequestMessage;
            String event = customMenuClickOrViewEventRequestMessage.getEvent();
            EventType eventType = EventType.valueBy(event);
            //自定义菜单点击事件类型
            if (eventType == EventType.EVENT_CUSTOM_MENU_CLICK) {

            } else if (eventType == EventType.EVENT_CUSTOM_MENU_VIEW) {//自定义菜单跳转事件类型

            }
        } else {
            LOGGER.error("no message type: [{}] found.", baseRequestMessage);
        }
        return null;
    }
}
