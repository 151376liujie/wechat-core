package com.jonnyliu.proj.wechat.message.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: jonny
 * Time: 2017-08-18 18:01.
 */
@Data
public class ScanCodeInfo implements Serializable {

    @XStreamAlias("ScanType")
    private String scanType;

    @XStreamAlias("ScanResult")
    private String scanResult;
}
