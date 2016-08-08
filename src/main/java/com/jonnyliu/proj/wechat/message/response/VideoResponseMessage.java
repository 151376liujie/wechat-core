package com.jonnyliu.proj.wechat.message.response;

/**
 * 回复语音消息的封装
 * Created by liujie-ds8 on 2016/8/5.
 */
public class VideoResponseMessage extends BaseResponseMessage {

    private Video Video;

    public void setVideo(Video video) {
        Video = video;
    }

    public Video getVideo() {
        return Video;
    }
}
