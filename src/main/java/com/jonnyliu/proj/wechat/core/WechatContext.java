package com.jonnyliu.proj.wechat.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * 微信的上下文，提供对spring的applicationContext的获取
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-09-06 12:40.
 */
public class WechatContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatContext.class);

    private static final ApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
