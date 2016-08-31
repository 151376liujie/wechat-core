package com.jonnyliu.proj.wechat.enums;

import com.jonnyliu.proj.wechat.utils.MappedEnumUtils;

import java.util.Map;

/**
 * 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-25 17:27.
 */
public enum Lang implements IndexedEnum {

    CHINESE("zh_CN", "简体"),
    TAIWAN("zh_TW", "繁体"),
    ENGLISH("en", "英语");

    private static final Map<Object, Lang> INDEX_MAP = MappedEnumUtils.toMap(Lang.values());
    private String languageCode;
    private String languageName;

    Lang(String languageCode, String languageName) {
        this.languageCode = languageCode;
        this.languageName = languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    @Override
    public Object getIndex() {
        return languageCode;
    }

    public static Lang indexOf(int code) {
        return MappedEnumUtils.indexOf(INDEX_MAP, code);
    }
}
