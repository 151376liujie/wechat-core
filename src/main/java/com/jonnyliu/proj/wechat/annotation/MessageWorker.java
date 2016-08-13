package com.jonnyliu.proj.wechat.annotation;

import com.jonnyliu.proj.wechat.enums.MessageType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liujie on 2016/8/13 11:29.
 * 用来标注真正处理消息的处理器，该注解只能标注在 <br/>
 *
 * @see com.jonnyliu.proj.wechat.handler.AbstractMessageHandler  AbstractMessageHandler<br/>
 * 的子类下
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MessageWorker {

    //标识要处理的消息类型
    MessageType type() default MessageType.TEXT_MESSAGE;

}
