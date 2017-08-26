package com.jonnyliu.proj.wechat.core;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class WechatContextListener implements ServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.getServletContext();
        if (log.isInfoEnabled()) {
            log.info("servletContext :{} started.", this.servletContext);
        }
        setWecharProperties();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (log.isInfoEnabled()) {
            log.info("servletContext :{} is going to destroy...", this.servletContext.getServletContextName());
        }
    }

    private void setWecharProperties() {
        InputStream resourceAsStream = this.servletContext.getResourceAsStream("/WEB-INF/classes/wechat.properties");
        if (resourceAsStream == null) {
            if (log.isWarnEnabled()) {
                log.warn("there is no properties file named :{}.it must be under /WEB-INF/classes/ ...");
            }
            return;
        }
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                servletContext.setAttribute(entry.getKey().toString(), entry.getValue());
            }
        } catch (IOException e) {
            log.error("error parsed wechat.properties...", e);
        }
    }

}
