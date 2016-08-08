package com.jonnyliu.proj.wechat.handler;

import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.TextRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import com.jonnyliu.proj.wechat.utils.SpliderUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 文本消息处理器
 * Created by jonnyliu on 2016/8/6 10:57.
 */
public class TextMessageHandler extends AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextMessageHandler.class);

    public BaseResponseMessage doHandleMessage(BaseRequestMessage requestMessage) {
        if (requestMessage instanceof TextRequestMessage) {
            TextRequestMessage textRequestMessage = (TextRequestMessage) requestMessage;
            String requestContent = textRequestMessage.getContent();
            if ("1".equalsIgnoreCase(requestContent)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String content = "当前时间为：" + simpleDateFormat.format(new Date());
                return MessageUtils.buildTextResponseMessage(requestMessage, content);
            } else if ("2".equalsIgnoreCase(requestContent)) {
                List<String> list = SpliderUtils.parseUrl();
                if (list == null || list.isEmpty()) {
                    return MessageUtils.buildTextResponseMessage(requestMessage, "不好意思哦，出了点小状况正在修复中，客官请稍等哦！！！");
                }
                return MessageUtils.buildTextResponseMessage(requestMessage, list.get(0));
            } else if (requestContent.startsWith("3@") && requestContent.length() >= 2) {
                String song = requestContent.split("@")[1];
                if (StringUtils.isEmpty(song)) {
                    return MessageUtils.buildTextResponseMessage(requestMessage, "您输入的歌名不正确，请重新发送！！");
                }

            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("①\t发送1报时").append("\n");
                stringBuilder.append("②\t发送2讲笑话").append("\n");
                stringBuilder.append("③\t发送3+@+歌名帮你搜歌，例如\"3@爱你一万年\"").append("\n");
                stringBuilder.append("\t直接给我发图片，我再给你发出去！").append("\n");
                return MessageUtils.buildTextResponseMessage(requestMessage, stringBuilder.toString());
            }
        }
        return null;
    }
}
