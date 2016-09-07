package com.jonnyliu.proj.wechat.listener;

import com.jonnyliu.proj.wechat.core.MessageHandlerLoader;
import com.jonnyliu.proj.wechat.utils.ClassPathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 一个加载消息处理器映射的监听器
 * author:980463316@qq.com <br/>
 * Created on 2016-08-20 17:01.
 */
public class MessageHandlerMappingListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerMappingListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("message handler mapping initial...");
        }
        //初始化消息处理器的映射，避免每次分发消息处理器时遍历classpath查找MessageWork注解对应的MessageHandler类
        ClassPathUtils.loadAndInitClass(MessageHandlerLoader.class.getName());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("message handler mapping destroyed...");
        }
    }
}
