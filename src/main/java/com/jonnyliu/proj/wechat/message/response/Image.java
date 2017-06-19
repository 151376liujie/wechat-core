package com.jonnyliu.proj.wechat.message.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by liujie on 2016/8/6 9:42.
 */
@Data
public class Image implements Serializable {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String MediaId;

}
