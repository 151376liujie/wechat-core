package com.jonnyliu.proj.wechat.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * 微信上下文监听器,需要在web.xml中配置
 * Author: jonny
 * Time: 2017-05-14 14:38.
 */
public class WechatContextListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatContextListener.class);

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.getServletContext();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("servletContext :{} started.",this.servletContext);
        }
        setWecharProperties();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("servletContext :{} is going to destroy...", this.servletContext.getServletContextName());
        }
    }

    private void setWecharProperties(){
        InputStream resourceAsStream = this.servletContext.getResourceAsStream("/WEB-INF/classes/wechat.properties");
        if (resourceAsStream == null){
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("there is no properties file named :{}.it must be under /WEB-INF/classes/ ...");
            }
            return ;
        }
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                servletContext.setAttribute(entry.getKey().toString(),entry.getValue());
            }
        } catch (IOException e) {
            LOGGER.error("error parsed wechat.properties...",e);
        }
    }

}
