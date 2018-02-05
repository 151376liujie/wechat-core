package com.jonnyliu.proj.wechat.message.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 回复语音消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
@Data
@XStreamAlias("xml")
public class VideoResponseMessage extends BaseResponseMessage {

    @XStreamAlias("Video")
    private Video video;

}
