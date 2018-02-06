package com.jonnyliu.proj.wechat.core;

import com.jonnyliu.proj.wechat.bean.MessageHandlerElement;
import com.jonnyliu.proj.wechat.config.WechatConfig;
import com.jonnyliu.proj.wechat.converter.MessageConvert;
import com.jonnyliu.proj.wechat.exception.NoMessageHandlerFoundException;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import com.jonnyliu.proj.wechat.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信消息处理器的入口 <br/>
 * author : 980463316@qq.com <br/>
 * Created on 2016/8/5 16:29
 */
@Slf4j
@RestController
@RequestMapping(value = "/wechat")
public class WechatController {

    @Autowired
    private MessageHandlerAdapter messageHandlerAdapter;

    @Autowired
    private MessageConvert messageConverter;

    @Autowired
    private WechatConfig wechatConfig;

    /**
     * 接收微信服务器的get请求
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(@RequestParam(value = "signature") String signature,
                        @RequestParam(value = "timestamp") String timestamp,
                        @RequestParam(value = "nonce") String nonce,
                        @RequestParam(value = "echostr") String echostr) {

        if (SignUtil.checkSignature(this.wechatConfig.getToken(), signature, timestamp, nonce)) {
            return echostr;
        }
        return "";
    }

    /**
     * 接收微信服务器的post请求并响应
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces = "text/xml;charset=utf-8")
    public String doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServletInputStream inputStream = request.getInputStream();
            String xml = IOUtils.toString(inputStream);
            String msgType = MessageUtils.getMessageType(xml);
            String eventType = MessageUtils.getEventType(xml);
            MessageHandlerElement messageHandlerElement = new MessageHandlerElement(msgType, eventType);
            //将用户发过来的消息转换成消息对象
            BaseRequestMessage requestMessage = this.messageConverter.doConvert(xml);
            //将不同类型的消息发送给不同的消息处理器
            AbstractMessageHandler messageHandler = this.messageHandlerAdapter.findMessageHandler(messageHandlerElement);
            if (messageHandler == null) {
                throw new NoMessageHandlerFoundException("no message handler found for message type " + msgType + " and event type " + eventType);
            }
            //调用消息处理器处理消息
            BaseResponseMessage responseMessage = messageHandler.handleMessage(requestMessage);
            if (responseMessage == null) {
                return "";
            }
            //构造给用户的响应消息
            String responseXml = MessageUtils.messageToXml(responseMessage);
            if (log.isDebugEnabled()) {
                log.debug("response xml : {}", responseXml);
            }
            return responseXml;
        } catch (NoMessageHandlerFoundException e) {
            log.error(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

}
