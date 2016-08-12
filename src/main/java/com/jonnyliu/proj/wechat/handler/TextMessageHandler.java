package com.jonnyliu.proj.wechat.handler;

import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.TextRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;

/**
 * 文本消息处理器
 * Created by jonnyliu on 2016/8/6 10:57.
 */
public class TextMessageHandler extends AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextMessageHandler.class);

    public BaseResponseMessage doHandleMessage(BaseRequestMessage requestMessage) {
        if (requestMessage instanceof TextRequestMessage) {
            //在这里实现你自己的业务逻辑
        }
        return null;
    }

    public static void main(String[] args){
        URL resource = TextMessageHandler.class.getClassLoader().getResource("");
        String file = resource.getFile();
        System.out.println(file);
        File file1 = new File(file);
        File[] list = file1.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory() || pathname.getName().endsWith(".class"))
                    return true;
                return false;
            }
        });
        for (File classFile:list){
            if (classFile.isDirectory()){
            }else {
                try {
                    String path = classFile.getPath();
                    Class<?> aClass = Class.forName(path.replaceAll("\\.", "\\"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
