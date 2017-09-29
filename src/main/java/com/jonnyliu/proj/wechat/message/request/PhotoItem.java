package com.jonnyliu.proj.wechat.message.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: jonny
 * Time: 2017-08-27 21:07.
 */
@Data
public class PhotoItem implements Serializable {
    /**
     * 图片的MD5值，开发者若需要，可用于验证接收到图片
     */
    private String PicMd5Sum;

}
