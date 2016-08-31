package com.jonnyliu.proj.wechat.utils;

import com.jonnyliu.proj.wechat.enums.IndexedEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举工具类，用来通过code获取枚举值（map实现）
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-31 15:42.
 */
public final class MappedEnumUtils {

    public static <T extends IndexedEnum> Map<Object, T> toMap(T[] ele) {
        Map<Object, T> map = new HashMap<>();
        for (T indexedEnum : ele) {
            map.put(indexedEnum.getIndex(), indexedEnum);
        }
        return map;
    }

    public static <T extends IndexedEnum> T indexOf(Map<Object, T> map, Object index) {
        return map.get(index);
    }

}
