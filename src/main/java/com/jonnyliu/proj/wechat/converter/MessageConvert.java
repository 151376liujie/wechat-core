package com.jonnyliu.proj.wechat.converter;

import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;

/**
 * 消息转换器，将不同类型的消息转换成不同类型的消息对象
 * author : 980463316@qq.com <br/>
 * Created on 2016/8/6 15:26.
 */
public interface MessageConvert {

    /**
     * 将用户发送的请求stream解析为消息对象
     *
     * @param xml 请求xml数据
     * @return 消息对象
     * @throws Exception
     */
    BaseRequestMessage doConvert(String xml) throws Exception;

}
