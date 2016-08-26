package com.jonnyliu.proj.wechat.bean.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-26 17:13.
 */
public class BaseBean implements Serializable {


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
