package com.jonnyliu.proj.wechat.utils;

import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.response.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息工具类
 * Created by liujie on 2016/8/6 9:25.
 */
public class MessageUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtils.class);

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * 解析inputStream 返回map
     *
     * @param inputStream
     * @return
     */
    public static Map<String, String> parseRequest(InputStream inputStream) {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                map.put(element.getName(), element.getTextTrim());
            }
        } catch (DocumentException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return map;
    }

    /**
     * 将相应消息转换成xml字符串
     *
     * @param baseResponseMessage
     * @return
     */
    public static String messageToXml(BaseResponseMessage baseResponseMessage) {
        String msgType = baseResponseMessage.getMsgType();
        MessageType messageType = MessageType.valueBy(msgType);
        switch (messageType) {
            case TEXT_MESSAGE:
                return textMessageToXml((TextResponseMessage) baseResponseMessage);
            case IMAGE_MESSAGE:
                return imageMessageToXml((ImageResponseMessage) baseResponseMessage);
            case VIDEO_MESSAGE:
                return videoMessageToXml((VideoResponseMessage) baseResponseMessage);
            case VOICE_MESSAGE:
                return voiceMessageToXml((VoiceResponseMessage) baseResponseMessage);
            case MUSIC_MESSAGE:
                return musicMessageToXml((MusicResponseMessage) baseResponseMessage);
            case NEWS_MESSAGE:
                return newsMessageToXml((MusicResponseMessage) baseResponseMessage);
        }
        return xstream.toXML(baseResponseMessage);
    }

    /**
     * 文本消息转xml
     *
     * @param textMessage 文本消息对象
     * @return xml字符串
     */
    public static String textMessageToXml(TextResponseMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 图片消息转xml
     *
     * @param imageMessage
     * @return xml字符串
     */
    public static String imageMessageToXml(ImageResponseMessage imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * 语音消息转xml
     *
     * @param voiceMessage 语音消息对象
     * @return xml字符串
     */
    public static String voiceMessageToXml(VoiceResponseMessage voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }

    /**
     * 视频消息转xml
     *
     * @param videoMessage 视频消息对象
     * @return xml字符串
     */
    public static String videoMessageToXml(VideoResponseMessage videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }

    /**
     * 音乐消息转xml
     *
     * @param musicMessage 音乐消息对象
     * @return xml字符串
     */
    public static String musicMessageToXml(MusicResponseMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 图文消息转xml
     *
     * @param newsMessage 图文消息对象
     * @return xml字符串
     */
    public static String newsMessageToXml(MusicResponseMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", Article.class);
        return xstream.toXML(newsMessage);
    }

    /**
     * 根据指定文本内容构建文本响应消息
     *
     * @param requestMessage
     * @param content
     * @return
     */
    public static TextResponseMessage buildTextResponseMessage(BaseRequestMessage requestMessage, String content) {
        TextResponseMessage textResponseMessage = new TextResponseMessage();
        textResponseMessage.setContent(content);
        textResponseMessage.setCreateTime(System.currentTimeMillis());
        textResponseMessage.setFromUserName(requestMessage.getToUserName());
        textResponseMessage.setToUserName(requestMessage.getFromUserName());
        textResponseMessage.setMsgType(MessageType.TEXT_MESSAGE.getTypeStr());
        return textResponseMessage;
    }

    /**
     * 填充基础消息对象，并返回
     *
     * @param xmlMap
     * @param requestMessage
     * @return
     */
    public static BaseRequestMessage inflateBaseRequestMessage(Map<String, String> xmlMap, BaseRequestMessage requestMessage) {
        String msgType = xmlMap.get("MsgType");
        String msgId = xmlMap.get("MsgId");
        String toUserName = xmlMap.get("ToUserName");
        String fromUserName = xmlMap.get("FromUserName");
        String createTime = xmlMap.get("CreateTime");
        requestMessage.setCreateTime(Long.valueOf(createTime));
        requestMessage.setMsgType(msgType);
        requestMessage.setMsgId(Long.valueOf(msgId));
        requestMessage.setFromUserName(fromUserName);
        requestMessage.setToUserName(toUserName);
        return requestMessage;
    }

    public static ImageResponseMessage buildImageResponseMessage(BaseRequestMessage baseRequestMessage, String mediaId, String picUrl) {
        ImageResponseMessage imageResponseMessage = new ImageResponseMessage();
        imageResponseMessage.setMsgType(MessageType.IMAGE_MESSAGE.getTypeStr());
        imageResponseMessage.setToUserName(baseRequestMessage.getFromUserName());
        imageResponseMessage.setCreateTime(System.currentTimeMillis());
        imageResponseMessage.setFromUserName(baseRequestMessage.getToUserName());
        Image image = new Image();
        image.setMediaId(mediaId);
        imageResponseMessage.setImage(image);
        return imageResponseMessage;
    }
}
