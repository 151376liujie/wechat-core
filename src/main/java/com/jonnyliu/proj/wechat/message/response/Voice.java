package com.jonnyliu.proj.wechat.message.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 单条音频消息
 * Created by liujie on 2016/8/6 0:06.
 */
@Data
public class Voice implements Serializable {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String MediaId;

}
