package com.jonnyliu.proj.wechat.enums;

import com.jonnyliu.proj.wechat.utils.MappedEnumUtils;

import java.util.Map;

/**
 * 性别的枚举
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-25 17:03.
 */
public enum Sex implements IndexedEnum {


    MALE(1, "男性"),
    FEMALE(2, "女性"),
    UNKNOWN(0, "未知");

    private static final Map<Object, Sex> INDEX_MAP = MappedEnumUtils.toMap(Sex.values());
    private int code;
    private String info;

    Sex(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public static Sex indexOf(int code) {
        return MappedEnumUtils.indexOf(INDEX_MAP, code);
    }

    @Override
    public Object getIndex() {
        return code;
    }

}
