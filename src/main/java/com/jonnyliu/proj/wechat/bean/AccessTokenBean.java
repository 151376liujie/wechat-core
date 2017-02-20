package com.jonnyliu.proj.wechat.bean;

/**
 * 封装请求得到的access_token
 * author:980463316@qq.com <br/>
 * Created on 2016-08-24 23:04.
 */
public class AccessTokenBean extends BaseBean {

    private String access_token;
    /**
     * 凭证有效期，单位：秒
     */
    private long expires_in;

    /**
     * 提前多少秒失效
     */
    private static final int TIMEDELTA = 200;

    /**
     * 有效期最大的时间毫秒值（单位：毫秒）
     */
    private long deadTime;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    /**
     * 在调用ObjectMapper.readValue()方法时该方法会被执行，从而为deadTime等属性设置了初始值
     * @param expires_in
     */
    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in - TIMEDELTA;
        long time = System.currentTimeMillis() + this.expires_in * 1000;
        setDeadTime(time);
    }

    public void setDeadTime(long deadTime) {
        this.deadTime = deadTime;
    }

    public long getDeadTime() {
        return deadTime;
    }
}
