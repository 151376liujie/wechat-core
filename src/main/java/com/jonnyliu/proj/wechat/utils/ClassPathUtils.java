package com.jonnyliu.proj.wechat.utils;

import com.jonnyliu.proj.wechat.handler.AbstractMessageHandler;
import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * 加载classpath下的类文件工具 <br/>
 * author : 980463316@qq.com <br/>
 * Created on 2016/8/13 9:08.
 */
public class ClassPathUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassPathUtils.class);


    /**
     * 加载指定名称的类，并指定是否执行静态初始代码块
     *
     * @param className 类名
     * @param initial   true表示执行初始代码块，otherwise 不执行初始代码块
     * @return 执行类名的Class对象
     */
    public static Class<?> loadClass(String className, boolean initial) {

        try {
            Class<?> aClass = ClassUtils.getClass(className, initial);
            return aClass;
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

    }

    /**
     * 加载并执行静态初始代码块
     *
     * @param className
     * @return
     */
    public static Class<?> loadAndInitClass(String className) {
        return loadClass(className, true);
    }


    /**
     * 加载指定路径下所有类
     *
     * @param path
     */
    public static Set<Class<?>> getClassesInPath(String path) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            Enumeration<URL> urlEnumeration = Thread.currentThread().getContextClassLoader().getResources(path);
            while (urlEnumeration.hasMoreElements()) {
                URL url = urlEnumeration.nextElement();
                String protocol = url.getProtocol();
                if ("file".equalsIgnoreCase(protocol)) {
                    String urlFile = url.getFile();
                    urlFile = URLDecoder.decode(urlFile, "utf-8");
                    classSet.addAll(addClassInFile(urlFile));
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return classSet;
    }

    /**
     * 加载某个目录下所有的类文件
     *
     * @param path 文件目录
     * @return 类class的集合
     */
    public static Set<Class<?>> addClassInFile(String path) {
        Set<Class<?>> classSet = new HashSet<>();
        File file = null;
        try {
            file = new File(URLDecoder.decode(path, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            file = new File(path);
        }
        if (file.isFile() && file.getName().endsWith(".class")) {
            String absolutePath = file.getAbsolutePath();
            int indexof = absolutePath.lastIndexOf("classes") + 8;
            int endIndex = absolutePath.lastIndexOf(".");
            String className = absolutePath.substring(indexof, endIndex);
            className = className.replace("\\", ".");
            classSet.add(loadAndInitClass(className));
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File ff : listFiles) {
                    classSet.addAll(addClassInFile(ff.getAbsolutePath()));
                }
            }
        }
        return classSet;
    }

    /**
     * 根据类上是否有MessageWorker注解来区分类
     *
     * @param annotationClass
     * @return
     */
    public static Set<Class<? extends AbstractMessageHandler>> getClassesByAnnotation(Class<? extends Annotation> annotationClass) {

        //加载类路径下所有类
        Set<Class<?>> classSet = getClassesInPath("");
        Set<Class<? extends AbstractMessageHandler>> returnClasses = new HashSet<>();
        for (Class clazz : classSet) {
            if (!clazz.isAnnotation() && clazz.isAnnotationPresent(annotationClass)) {
                returnClasses.add(clazz);
            }
        }
        return returnClasses;
    }

}
