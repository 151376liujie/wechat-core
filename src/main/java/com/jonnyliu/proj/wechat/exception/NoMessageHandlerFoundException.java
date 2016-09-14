package com.jonnyliu.proj.wechat.exception;

/**
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-09-14 18:31.
 */
public class NoMessageHandlerFoundException extends Exception {

    public NoMessageHandlerFoundException(String message) {
        super(message);
    }
}
