package com.jonnyliu.proj.wechat;

import com.jonnyliu.proj.wechat.annotation.MessageProcessor;
import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import com.jonnyliu.proj.wechat.utils.ClassPathUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by liujie on 2016/8/13 9:20.
 */
public class ClassPathUtilsTest {

    @Test
    public void testLoadClass() {

        Class<?> aClass = ClassPathUtils.loadClass("com.jonnyliu.proj.wechat.Foo", true);

        Assert.assertNotNull(aClass);

    }


    @Test
    public void testGetClassesInPath() {
        Set<Class<?>> classesInPath = ClassPathUtils.getClassesInPath("");
        System.out.println("classesInPath:==>" + classesInPath);
        Assert.assertTrue(!classesInPath.isEmpty());
    }

    @Test
    public void testGetClassByAnnotation() {
        Set<Class<? extends AbstractMessageHandler>> classesByAnnotation =
                ClassPathUtils.getClassesByAnnotation(MessageProcessor.class);
        System.out.println(classesByAnnotation);
    }
}
