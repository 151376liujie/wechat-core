package com.jonnyliu.proj.wechat.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 键值对
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-18 14:54.
 */
public class NameAndValuePair<K,V> implements Serializable {

    private K key;

    private V value;

    public NameAndValuePair(K key,V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
