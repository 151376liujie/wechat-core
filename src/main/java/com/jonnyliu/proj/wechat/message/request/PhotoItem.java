package com.jonnyliu.proj.wechat.message.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: jonny
 * Time: 2017-08-27 21:07.
 */
@Data
@XStreamAlias("item")
public class PhotoItem implements Serializable {
    /**
     * 图片的MD5值，开发者若需要，可用于验证接收到图片
     */
    @XStreamAlias("PicMd5Sum")
    private String picMd5Sum;

}
