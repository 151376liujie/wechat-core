package com.jonnyliu.proj.wechat.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: jonny
 * Time: 2018-02-06 22:56.
 */
@Data
@AllArgsConstructor
public class MessageHandlerElement implements Serializable {

    private String messageType;

    private String eventType;

}
