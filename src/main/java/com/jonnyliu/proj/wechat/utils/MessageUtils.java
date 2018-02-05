package com.jonnyliu.proj.wechat.utils;

import com.jonnyliu.proj.wechat.enums.MessageType;
import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.response.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 消息工具类
 * Created by liujie on 2016/8/6 9:25.
 */
public class MessageUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtils.class);

    /**
     * 解析消息类型字段的正则表达式
     */
    private static final Pattern MESSAGE_TYPE_PATTERN = Pattern.compile("\\<MsgType\\>\\<\\!\\[CDATA\\[(.*?)\\]\\]\\>\\<\\/MsgType\\>");

    /**
     * 解析事件类型字段的正则表达式
     */
    private static final Pattern EVENT_TYPE_PATTERN = Pattern.compile("\\<Event\\>\\<\\!\\[CDATA\\[(.*?)\\]\\]\\>\\<\\/Event\\>");

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream newXStreamInstance() {
        return new XStream(new XppDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    // 对所有xml节点的转换都增加CDATA标记
                    boolean cdata = true;

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (this.cdata) {
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
    }

    /**
     * 将相应消息转换成xml字符串
     *
     * @param baseResponseMessage
     * @return
     */
    public static String messageToXml(BaseResponseMessage baseResponseMessage) {
        XStream xStream = newXStreamInstance();
        xStream.processAnnotations(baseResponseMessage.getClass());
        return xStream.toXML(baseResponseMessage);
    }

    /**
     * 文本消息转xml
     *
     * @param textMessage 文本消息对象
     * @return xml字符串
     */
    public static String textMessageToXml(TextResponseMessage textMessage) {
        XStream xstream = newXStreamInstance();
        xstream.processAnnotations(textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 图片消息转xml
     *
     * @param imageMessage
     * @return xml字符串
     */
    public static String imageMessageToXml(ImageResponseMessage imageMessage) {
        XStream xstream = newXStreamInstance();
        xstream.processAnnotations(imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * 语音消息转xml
     *
     * @param voiceMessage 语音消息对象
     * @return xml字符串
     */
    public static String voiceMessageToXml(VoiceResponseMessage voiceMessage) {
        XStream xstream = newXStreamInstance();
        xstream.processAnnotations(voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }

    /**
     * 视频消息转xml
     *
     * @param videoMessage 视频消息对象
     * @return xml字符串
     */
    public static String videoMessageToXml(VideoResponseMessage videoMessage) {
        XStream xstream = newXStreamInstance();
        xstream.processAnnotations(videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }

    /**
     * 音乐消息转xml
     *
     * @param musicMessage 音乐消息对象
     * @return xml字符串
     */
    public static String musicMessageToXml(MusicResponseMessage musicMessage) {
        XStream xstream = newXStreamInstance();
        xstream.processAnnotations(musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 图文消息转xml
     *
     * @param newsMessage 图文消息对象
     * @return xml字符串
     */
    public static String newsMessageToXml(NewsResponseMessage newsMessage) {
        XStream xstream = newXStreamInstance();
        xstream.processAnnotations(newsMessage.getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * 根据指定文本内容构建<strong>文本</strong>响应消息
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
     * 构建<strong>图片</strong>响应消息
     *
     * @param baseRequestMessage
     * @param paramMap           参数的键值对
     * @return
     */
    public static ImageResponseMessage buildImageResponseMessage(BaseRequestMessage baseRequestMessage, Map<String, String> paramMap) {
        ImageResponseMessage imageResponseMessage = new ImageResponseMessage();
        imageResponseMessage.setMsgType(MessageType.IMAGE_MESSAGE.getTypeStr());
        imageResponseMessage.setToUserName(baseRequestMessage.getFromUserName());
        imageResponseMessage.setCreateTime(System.currentTimeMillis());
        imageResponseMessage.setFromUserName(baseRequestMessage.getToUserName());
        Image image = new Image();
        image.setMediaId(paramMap.get("MediaId") == null ? "" : paramMap.get("MediaId"));
        imageResponseMessage.setImage(image);
        return imageResponseMessage;
    }

    /**
     * 根据参数构建<strong>音乐</strong>回复消息
     *
     * @param baseRequestMessage
     * @param paramMap
     * @return
     */
    public static MusicResponseMessage buildMusicResponseMessage(BaseRequestMessage baseRequestMessage, Map<String, String> paramMap) {
        MusicResponseMessage musicResponseMessage = new MusicResponseMessage();
        musicResponseMessage.setCreateTime(System.currentTimeMillis());
        musicResponseMessage.setFromUserName(baseRequestMessage.getToUserName());
        musicResponseMessage.setMsgType(MessageType.MUSIC_MESSAGE.getTypeStr());
        musicResponseMessage.setToUserName(baseRequestMessage.getFromUserName());
        Music music = new Music();
        music.setDescription(paramMap.get("Description") == null ? "" : paramMap.get("Description"));
        music.setHQMusicUrl(paramMap.get("HQMusicUrl") == null ? "" : paramMap.get("HQMusicUrl"));
        music.setMusicURL(paramMap.get("MusicUrl") == null ? "" : paramMap.get("MusicUrl"));
        music.setThumbMediaId(paramMap.get("ThumbMediaId") == null ? "" : paramMap.get("ThumbMediaId"));
        music.setTitle(paramMap.get("Title") == null ? "" : paramMap.get("Title"));
        musicResponseMessage.setMusic(music);
        return musicResponseMessage;
    }

    /**
     * 根据参数构建<strong>语音</strong>回复消息
     *
     * @param baseRequestMessage
     * @param paramMap
     * @return
     */
    public static VoiceResponseMessage buildVoiceResponseMessage(BaseRequestMessage baseRequestMessage, Map<String, String> paramMap) {
        VoiceResponseMessage voiceResponseMessage = new VoiceResponseMessage();
        voiceResponseMessage.setToUserName(baseRequestMessage.getFromUserName());
        voiceResponseMessage.setFromUserName(baseRequestMessage.getToUserName());
        voiceResponseMessage.setMsgType(MessageType.VOICE_MESSAGE.getTypeStr());
        voiceResponseMessage.setCreateTime(System.currentTimeMillis());
        Voice voice = new Voice();
        voice.setMediaId(paramMap.get("MediaId") == null ? "" : paramMap.get("MediaId"));
        voiceResponseMessage.setVoice(voice);
        return voiceResponseMessage;
    }

    /**
     * 根据参数构建<strong>视频、短视频消息</strong>
     *
     * @param baseRequestMessage
     * @param paramMap
     * @return
     */
    public static VideoResponseMessage buildVideoResponseMessage(BaseRequestMessage baseRequestMessage, Map<String, String> paramMap) {
        VideoResponseMessage videoResponseMessage = new VideoResponseMessage();
        videoResponseMessage.setCreateTime(System.currentTimeMillis());
        videoResponseMessage.setToUserName(baseRequestMessage.getFromUserName());
        videoResponseMessage.setFromUserName(baseRequestMessage.getToUserName());
        videoResponseMessage.setMsgType(MessageType.VIDEO_MESSAGE.getTypeStr());
        Video video = new Video();
        video.setMediaId(paramMap.get("MediaId") == null ? "" : paramMap.get("MediaId"));
        video.setDescription(paramMap.get("Description") == null ? "" : paramMap.get("Description"));
        video.setTitle(paramMap.get("Title") == null ? "" : paramMap.get("Title"));
        videoResponseMessage.setVideo(video);
        return videoResponseMessage;
    }

    /**
     * 根据参数构建<strong>图文消息</strong>
     *
     * @param baseRequestMessage
     * @param paramMap
     * @return
     */
    public static NewsResponseMessage buildNewsResponseMessage(BaseRequestMessage baseRequestMessage, Map<String, String> paramMap, List<Article> articles) {
        NewsResponseMessage newsResponseMessage = new NewsResponseMessage();
        newsResponseMessage.setCreateTime(System.currentTimeMillis());
        newsResponseMessage.setToUserName(baseRequestMessage.getFromUserName());
        newsResponseMessage.setFromUserName(baseRequestMessage.getToUserName());
        newsResponseMessage.setMsgType(MessageType.NEWS_MESSAGE.getTypeStr());
        String articleCount = paramMap.get("ArticleCount");
        int articleNum = articles == null ? 0 : articles.size();
        if (StringUtils.isNumeric(articleCount)) {
            articleNum = Integer.parseInt(articleCount);
        }
        newsResponseMessage.setArticleCount(articleNum);
        newsResponseMessage.setArticles(articles);
        return newsResponseMessage;
    }

    public static String getMessageType(String xml) {
        Matcher matcher = MESSAGE_TYPE_PATTERN.matcher(xml);
        return matcher.find() ? matcher.group(1) : null;
    }

    public static String getEventType(String xml) {
        Matcher matcher = EVENT_TYPE_PATTERN.matcher(xml);
        return matcher.find() ? matcher.group(1) : null;
    }

    public static <T> T xml2Message(String xml, Class<T> clazz) {
        XStream xstream = newXStreamInstance();
        xstream.processAnnotations(clazz);
        return (T) xstream.fromXML(xml);
    }

    public static String toXml(Object message) {
        XStream xstream = newXStreamInstance();
        xstream.processAnnotations(message.getClass());
        return xstream.toXML(message);
    }
}
