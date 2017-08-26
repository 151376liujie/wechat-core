package com.jonnyliu.proj.wechat.message.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: jonny
 * Time: 2017-08-19 10:16.
 */
@Data
public class SendPicsInfo implements Serializable {

    private int Count;

    private String PicMd5Sum;

}
