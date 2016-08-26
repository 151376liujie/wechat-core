package com.jonnyliu.proj.wechat.enums;

/**
 * 性别的枚举
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-25 17:03.
 */
public enum Sex {

    MALE(1, "男性"),
    FEMALE(2, "女性"),
    UNKNOWN(0, "未知");

    private int code;
    private String info;

    Sex(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public static Sex valueOf(int code) {
        Sex[] values = Sex.values();
        for (Sex sex : values) {
            if (sex.getCode() == code) {
                return sex;
            }
        }
        return null;
    }

}
