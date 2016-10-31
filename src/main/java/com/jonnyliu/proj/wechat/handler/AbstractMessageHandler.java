package com.jonnyliu.proj.wechat.handler;

import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息处理器的抽象类，可继承该类实现在处理消息时执行一些额外工作，例如消息过滤 <br/>
 * author : 980463316@qq.com <br/>
 * Created on 2016/8/6 10:55.
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
            if (accept(requestMessage)) {
                preHandleMessage(requestMessage);
                baseResponseMessage = doHandleMessage(requestMessage);
            } else {
                if (LOGGER.isWarnEnabled()){
                    LOGGER.warn("based on your custom logic,the request message:{} will be ignored!", requestMessage);
                }
            }
        } catch (Exception e) {
            onError(requestMessage, e);
        } finally {
            if (baseResponseMessage != null) {
                postHandleMessage(requestMessage, baseResponseMessage);
            }
        }
        return baseResponseMessage;

    }

    /**
     * 消息处理器处理消息的前置检查条件，
     *
     * @param baseRequestMessage 请求的消息对象
     * @return 当该方法返回true时才会真正调用处理消息的方法，当方法返回false时，
     * 不执行真正处理消息的方法，也不会执行postHandleMessage方法，会直接返回null
     */
    protected boolean accept(BaseRequestMessage baseRequestMessage) {
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("request message : {} is checking for processing or not...", baseRequestMessage);
        }
        return true;
    }

    /**
     * 可重写该方法实现在处理消息前对消息进行处理
     *
     * @param requestMessage
     */
    protected void preHandleMessage(BaseRequestMessage requestMessage) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("用户发送给公众号的消息==>{}", requestMessage);
        }
    }

    /**
     * 可重写该方法实现在处理消息后对消息进行处理
     *
     * @param requestMessage
     * @param baseResponseMessage
     */
    protected void postHandleMessage(BaseRequestMessage requestMessage, BaseResponseMessage baseResponseMessage) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("公众号发给用户的响应消息为==>{}", baseResponseMessage);
        }
    }

    /**
     * 当处理消息的过程中出错时，将执行该方法（默认使用日志记录错误信息到控制台），可重写该方法实现自定义业务
     *
     * @param requestMessage
     * @param e
     */
    protected void onError(BaseRequestMessage requestMessage, Exception e) {
        LOGGER.error("error occured when processing request message: {}. and the error message is: {} ", requestMessage, e);
    }

}
