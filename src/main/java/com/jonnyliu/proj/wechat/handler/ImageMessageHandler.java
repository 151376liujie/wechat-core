package com.jonnyliu.proj.wechat.handler;

import com.jonnyliu.proj.wechat.message.request.BaseRequestMessage;
import com.jonnyliu.proj.wechat.message.request.ImageRequestMessage;
import com.jonnyliu.proj.wechat.message.response.BaseResponseMessage;
import com.jonnyliu.proj.wechat.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 图片消息处理器
 * Created by liujie on 2016/8/6 14:34.
 */
public class ImageMessageHandler extends AbstractMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageMessageHandler.class);


    @Override
    public BaseResponseMessage doHandleMessage(BaseRequestMessage baseRequestMessage) {
        if (baseRequestMessage instanceof ImageRequestMessage) {
            ImageRequestMessage imageRequestMessage = (ImageRequestMessage) baseRequestMessage;
            String mediaId = imageRequestMessage.getMediaId();
            String picUrl = imageRequestMessage.getPicUrl();
            LOGGER.info(imageRequestMessage.toString());
            return MessageUtils.buildImageResponseMessage(baseRequestMessage, mediaId, picUrl);
        }
        return null;
    }
}
