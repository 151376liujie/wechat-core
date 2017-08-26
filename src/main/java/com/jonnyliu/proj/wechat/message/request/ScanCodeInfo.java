package com.jonnyliu.proj.wechat.message.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: jonny
 * Time: 2017-08-18 18:01.
 */
@Data
public class ScanCodeInfo implements Serializable {

    private String ScanType;

    private String ScanResult;
}
