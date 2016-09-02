package com.jonnyliu.proj.wechat.service;

import com.jonnyliu.proj.wechat.bean.AccessTokenBean;
import com.jonnyliu.proj.wechat.service.accesstoken.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基本服务
 * author:980463316@qq.com <br/>
 * Created on 2016-09-02 23:28.
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private AccessTokenService accessTokenService;

    @Override
    public AccessTokenBean getAccessToken() {
        return accessTokenService.getAccessToken();
    }
}
