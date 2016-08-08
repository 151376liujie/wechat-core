package com.jonnyliu.proj.wechat.handler;

import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;

/**
 * 消息处理器接口
 * Created by liujie on 2016/8/6 17:36.
 */
public interface MessageHandler {

    /**
     * 真正的处理消息的方法
     *
     * @param baseRequestMessage
     * @return
     */
    BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage);
}
