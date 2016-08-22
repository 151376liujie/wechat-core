package com.jonnyliu.proj.wechat.example;

import com.jonnyliu.proj.wechat.annotation.MessageWorker;
import com.jonnyliu.proj.wechat.enums.EventType;
import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.SubOrUnSubEventRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;

/**
 * 事件消息处理器示例
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-22 13:47.
 */
@MessageWorker(type = MessageType.EVENT)
public class EventMessageHandlerExample extends AbstractMessageHandler {

    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {

        String msgType = baseRequestMessage.getMsgType();

        if (baseRequestMessage instanceof SubOrUnSubEventRequestMessage) {

            SubOrUnSubEventRequestMessage subOrUnSubEventRequestMessage = (SubOrUnSubEventRequestMessage) baseRequestMessage;
            String event = subOrUnSubEventRequestMessage.getEvent();

            EventType eventType = EventType.valueBy(event);

            switch (eventType) {
                //关注事件
                case EVENT_SUBSCRIBE:
                    return MessageUtils.buildTextResponseMessage(baseRequestMessage, "感谢您的关注！<a href=\"https://github.com/151376liujie/wechat-core\">微信开源项目地址</a>");
                //取消关注事件,推荐什么都不做，因为人家都取消关注了，即使回复消息的话人家也已经收不到了
                case EVENT_UNSUBSCRIBE:

                    //上传地理位置事件类型
                case EVENT_UPLOAD_LOCATION:
                    //在这里加上你自己的业务逻辑

                    //扫描关注事件类型
                case EVENT_SCAN_SUBSCRIBE:
                    //在这里加上你自己的业务逻辑

                    //自定义参数的二维码扫描事件类型
                case EVENT_SCAN:
                    //在这里加上你自己的业务逻辑

                    //自定义菜单跳转事件类型
                case EVENT_CUSTOM_MENU_VIEW:
                    //在这里加上你自己的业务逻辑

                    //自定义菜单点击事件类型
                case EVENT_CUSTOM_MENU_CLICK:
                    //在这里加上你自己的业务逻辑
            }

        }

        return null;
    }
}
