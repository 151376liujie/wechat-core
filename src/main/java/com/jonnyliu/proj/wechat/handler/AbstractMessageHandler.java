package com.jonnyliu.proj.wechat.handler;

import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息处理器接口
 * Created by liujie on 2016/8/6 10:55.
 */
public abstract class AbstractMessageHandler implements MessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMessageHandler.class);

    /**
     * 接收各类请求消息，并返回消息
     *
     * @param requestMessage
     * @return
     */
    public final BaseResponseMessage handleMessage(BaseRequestMessage requestMessage) {
        BaseResponseMessage baseResponseMessage = null;
        try {
            if (check(requestMessage)) {
                preHandleMessage(requestMessage);
                baseResponseMessage = doHandleMessage(requestMessage);
            }
        } catch (Exception e) {
            error(e);
        } finally {
            if (baseResponseMessage != null) {
                postHandleMessage(baseResponseMessage);
            }
        }
        return baseResponseMessage;

    }

    /**
     * 消息处理器处理消息的前置检查条件，
     *
     * @param baseRequestMessage 请求的消息对象
     * @return 当该方法返回true时才会真正调用处理消息的方法，当方法返回false时，不执行真正处理消息的方法，也不会执行postHandleMessage方法，会直接返回null
     */
    protected boolean check(BaseRequestMessage baseRequestMessage) {
        return true;
    }

    /**
     * 可重写该方法实现在处理消息前对消息进行处理
     *
     * @param requestMessage
     */
    protected void preHandleMessage(BaseRequestMessage requestMessage) {
        LOGGER.info("用户发送给公众号的消息==>" + requestMessage);
    }

    /**
     * 可重写该方法实现在处理消息后对消息进行处理
     *
     * @param baseResponseMessage
     */
    protected void postHandleMessage(BaseResponseMessage baseResponseMessage) {
        LOGGER.info("公众号发给用户的响应消息为==>" + baseResponseMessage);
    }

    /**
     * 当处理消息的过程中出错时，将执行该方法（默认使用日志记录错误信息到控制台），可重写该方法实现自定义业务
     *
     * @param e
     */
    protected void error(Exception e) {
        LOGGER.error("error occured when handling message...");
        LOGGER.error(e.getMessage(), e);
    }

}
