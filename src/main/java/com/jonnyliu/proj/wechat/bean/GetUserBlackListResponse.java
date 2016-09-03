package com.jonnyliu.proj.wechat.bean;

/**
 * 封装获取公众号黑名单列表的返回数据
 * author:980463316@qq.com <br/>
 * Created on 2016-09-04 00:05
 */
public class GetUserBlackListResponse extends BaseBean {

    private long total;

    private long count;

    private UserOpenIdList data;

    private String next_openid;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public UserOpenIdList getData() {
        return data;
    }

    public void setData(UserOpenIdList data) {
        this.data = data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }
}
