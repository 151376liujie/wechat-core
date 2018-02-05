package com.jonnyliu.proj.wechat.message.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 回复图片消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("xml")
public class ImageResponseMessage extends BaseResponseMessage {

    @XStreamAlias("Image")
    private Image image;

}
