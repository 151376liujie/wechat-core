package com.jonnyliu.proj.wechat.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonnyliu.proj.wechat.bean.*;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import com.jonnyliu.proj.wechat.service.accesstoken.AccessTokenService;
import com.jonnyliu.proj.wechat.utils.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<NameAndValuePair<String, String>> nameAndValuePairs = new ArrayList<>();
        nameAndValuePairs.add(new NameAndValuePair<>("access_token", accessToken.getAccess_token()));
        nameAndValuePairs.add(new NameAndValuePair<>("openid", userInfoParam.getOpenid()));
        nameAndValuePairs.add(new NameAndValuePair<>("lang", userInfoParam.getLang()));
        WechatUser wechatUser = null;
        try {
            String response = HttpClientUtils.sendGet(WechatConstant.WECHAT_USER_FETCH_URL, nameAndValuePairs);
            wechatUser = MAPPER.readValue(response, WechatUser.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return wechatUser;
    }

    @Override
    public List<WechatUser> batchGetWechatUserInfo(BatchGetUserRequestParam getUserParamList) {
        AccessTokenBean accessToken = accessTokenService.getAccessToken();
        if (accessToken == null) {
            accessToken = accessTokenService.refreshAccessToken();
        }
        if (getUserParamList == null) {
            return Collections.emptyList();
        }
        try {
            String paramJson = MAPPER.writeValueAsString(getUserParamList);
            Map<String, String> param = new HashMap<>();
            param.put("data", paramJson);
            String postResponse = HttpClientUtils.sendPost(WechatConstant.WECHAT_USER_BATCH_FETCH_URL + "?access_token=" + accessToken.getAccess_token(), param);
            if (StringUtils.isEmpty(postResponse)) {
                return Collections.emptyList();
            }
            BatchGetUserInfoResponse response = MAPPER.readValue(postResponse, BatchGetUserInfoResponse.class);
            return response.getUser_info_list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public CreateTagResponse createTag(String tagName) {
        AccessTokenBean accessToken = accessTokenService.getAccessToken();
        if (accessToken == null) {
            accessToken = accessTokenService.refreshAccessToken();
        }
        if (StringUtils.isEmpty(tagName)) {
            throw new RuntimeException("tagname must not be null or empty");
        }
        WechatTag tag = new WechatTag();
        tag.setName(tagName);
        CreateTagParameter parameter = new CreateTagParameter(tag);
        try {
            String json = MAPPER.writeValueAsString(parameter);
            Map<String, String> map = new HashMap<>();
            map.put("data", json);
            String response = HttpClientUtils.sendPost(WechatConstant.WECHAT_CREATE_TAG_URL + "?access_token=" + accessToken.getAccess_token(), map);
            CreateTagResponse createTagResponse = MAPPER.readValue(response, CreateTagResponse.class);
            return createTagResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
