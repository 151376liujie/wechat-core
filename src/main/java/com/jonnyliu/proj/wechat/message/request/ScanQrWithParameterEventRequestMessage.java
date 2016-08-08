package com.jonnyliu.proj.wechat.message.request;

/**
 * 扫描二维码事件的消息封装
 * Created by liujie-ds8 on 2016/8/5.
 */
public class ScanQrWithParameterEventRequestMessage extends BaseRequestMessage {

    /**
     * 事件类型，subscribe
     */
    private String Event;

    /**
     * (用户未关注时)事件KEY值，qrscene_为前缀，后面为二维码的参数值 <br/>
     * (用户已关注时)事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     */
    private String EventKey;


    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String Ticket;

}
