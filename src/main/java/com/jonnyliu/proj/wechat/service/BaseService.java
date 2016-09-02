package com.jonnyliu.proj.wechat.service;

import com.jonnyliu.proj.wechat.bean.AccessTokenBean;

/**
 * author:980463316@qq.com <br/>
 * Created on 2016-09-02 23:27.
 */
public interface BaseService {

    /**
     * 获取access token
     *
     * @return
     */
    AccessTokenBean getAccessToken();

}
