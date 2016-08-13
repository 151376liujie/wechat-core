package com.jonnyliu.proj.wechat.annotation;

import com.jonnyliu.proj.wechat.enums.MessageType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: 980463316@qq.com <br/>
 * created on 2016/8/13 11:29.
 * 用来标注真正处理消息的处理器，该注解只能标注在 AbstractMessageHandler的子类下
 * @see com.jonnyliu.proj.wechat.handler.AbstractMessageHandler AbstractMessageHandler <br/>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MessageWorker {

    //标识要处理的消息类型
    MessageType type() default MessageType.TEXT_MESSAGE;

}
