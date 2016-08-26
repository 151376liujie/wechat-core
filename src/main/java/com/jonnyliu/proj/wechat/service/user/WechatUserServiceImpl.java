package com.jonnyliu.proj.wechat.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonnyliu.proj.wechat.bean.AccessTokenBean;
import com.jonnyliu.proj.wechat.bean.BatchGetUserRequestParam;
import com.jonnyliu.proj.wechat.bean.GetUserInfoParam;
import com.jonnyliu.proj.wechat.bean.WechatUser;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import com.jonnyliu.proj.wechat.service.accesstoken.AccessTokenService;
import com.jonnyliu.proj.wechat.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信用户服务实现
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-25 17:36.
 */
@Service
public class WechatUserServiceImpl implements WechatUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatUserServiceImpl.class);

    @Autowired
    private AccessTokenService accessTokenService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public WechatUser getWechatUserInfo(GetUserInfoParam userInfoParam) {
        AccessTokenBean accessToken = accessTokenService.getAccessToken();
        if (accessToken == null) {
            accessToken = accessTokenService.refreshAccessToken();
        }
        Map<String, String> param = new HashMap<>();
        param.put("access_token", accessToken.getAccess_token());
        param.put("openid", userInfoParam.getOpenid());
        param.put("lang", userInfoParam.getLang());
        WechatUser wechatUser = null;
        try {
            String response = HttpClientUtils.sendGet(WechatConstant.WECHAT_USER_FETCH_URL, param);
            wechatUser = MAPPER.readValue(response, WechatUser.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return wechatUser;
    }

    @Override
    public List<WechatUser> batchGetWechatUserInfo(List<BatchGetUserRequestParam> getUserParamList) {
        AccessTokenBean accessToken = accessTokenService.getAccessToken();
        if (accessToken == null) {
            accessToken = accessTokenService.refreshAccessToken();
        }
        if (getUserParamList == null || getUserParamList.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            String paramJson = MAPPER.writeValueAsString(getUserParamList);
            Map<String, String> param = new HashMap<>();
            param.put("access_token", accessToken.getAccess_token());
            param.put("data", paramJson);
//            HttpClientUtils.sendGet(WechatConstant.WECHAT_USER_BATCH_FETCH_URL,param);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }


}
