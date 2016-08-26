package com.jonnyliu.proj.wechat.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonnyliu.proj.wechat.bean.AccessTokenBean;
import com.jonnyliu.proj.wechat.bean.WechatUser;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import com.jonnyliu.proj.wechat.enums.Lang;
import com.jonnyliu.proj.wechat.service.accesstoken.AccessTokenService;
import com.jonnyliu.proj.wechat.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public WechatUser getWechatUserInfo(String openid, Lang lang) {
        AccessTokenBean accessToken = accessTokenService.getAccessToken();
        if (accessToken == null) {
            accessToken = accessTokenService.refreshAccessToken();
        }
        Map<String, String> param = new HashMap<>();
        param.put("access_token", accessToken.getAccess_token());
        param.put("openid", openid);
        param.put("lang", lang.getLanguageCode());
        WechatUser wechatUser = null;
        try {
            String response = HttpClientUtils.sendGet(WechatConstant.WECHAT_USER_FETCH_URL, param);
            wechatUser = MAPPER.readValue(response, WechatUser.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return wechatUser;
    }


}
