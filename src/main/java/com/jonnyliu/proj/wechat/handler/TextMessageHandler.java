package com.jonnyliu.proj.wechat.handler;

import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.TextRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文本消息处理器
 * Created by jonnyliu on 2016/8/6 10:57.
 */
public class TextMessageHandler extends AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextMessageHandler.class);

    public BaseResponseMessage doHandleMessage(BaseRequestMessage requestMessage) {
        if (requestMessage instanceof TextRequestMessage) {
            //在这里实现你自己的业务逻辑
        }
        return null;
    }
}
