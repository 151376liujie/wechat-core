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
    public WechatUser getWechatUserInfo(GetUserInfoParameter userInfoParam) {
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
    public List<WechatUser> batchGetWechatUserInfo(BatchGetUserRequestParameter getUserParamList) {
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
            List<NameAndValuePair<String, String>> list = new ArrayList<>();
            list.add(new NameAndValuePair("access_token", accessToken.getAccess_token()));
            String postResponse = HttpClientUtils.sendPost(HttpClientUtils.buildUrlWithParam(WechatConstant.WECHAT_USER_BATCH_FETCH_URL, list, WechatConstant.DEFAULT_CHARSET), param);
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
        CreateOrEditTagParameter parameter = new CreateOrEditTagParameter(tag);
        try {
            String json = MAPPER.writeValueAsString(parameter);
            Map<String, String> map = new HashMap<>();
            map.put("data", json);
            List<NameAndValuePair<String, String>> list = new ArrayList<>();
            list.add(new NameAndValuePair("access_token", accessToken.getAccess_token()));
            String response = HttpClientUtils.sendPost(HttpClientUtils.buildUrlWithParam(WechatConstant.WECHAT_CREATE_TAG_URL, list, WechatConstant.DEFAULT_CHARSET), map);
            CreateTagResponse createTagResponse = MAPPER.readValue(response, CreateTagResponse.class);
            return createTagResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public GetWechatTagResponse getTags() {
        AccessTokenBean accessToken = accessTokenService.getAccessToken();
        if (accessToken == null) {
            accessToken = accessTokenService.refreshAccessToken();
        }
        try {
            List<NameAndValuePair<String, String>> nameAndValuePairs = new ArrayList<>();
            nameAndValuePairs.add(new NameAndValuePair<>("access_token", accessToken.getAccess_token()));
            String response = HttpClientUtils.sendGet(WechatConstant.WECHAT_GET_TAG_URL, nameAndValuePairs);
            GetWechatTagResponse getWechatTagResponse = MAPPER.readValue(response, GetWechatTagResponse.class);
            return getWechatTagResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public APIResponse editTag(CreateOrEditTagParameter parameter) {
        AccessTokenBean accessToken = accessTokenService.getAccessToken();
        if (accessToken == null) {
            accessToken = accessTokenService.refreshAccessToken();
        }
        Map<String, String> map = new HashMap<>();
        List<NameAndValuePair<String, String>> list = new ArrayList<>();
        list.add(new NameAndValuePair("access_token", accessToken.getAccess_token()));
        try {
            String postJson = MAPPER.writeValueAsString(parameter);
            map.put("data", postJson);
            String response = HttpClientUtils.sendPost(HttpClientUtils.buildUrlWithParam(WechatConstant.WECHAT_EDIT_TAG_URL, list, WechatConstant.DEFAULT_CHARSET), map);
            APIResponse apiResponse = MAPPER.readValue(response, APIResponse.class);
            return apiResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public APIResponse deleteTag(long tagId) {
        AccessTokenBean accessToken = accessTokenService.getAccessToken();
        if (accessToken == null) {
            accessToken = accessTokenService.refreshAccessToken();
        }
        if (tagId <= 0) {
            throw new RuntimeException("invalid tagid:[{" + String.valueOf(tagId) + "}]");
        }
        WechatTag tag = new WechatTag();
        tag.setId(tagId);
        CreateOrEditTagParameter parameter = new CreateOrEditTagParameter(tag);
        Map<String, String> map = new HashMap<>();
        List<NameAndValuePair<String, String>> list = new ArrayList<>();
        list.add(new NameAndValuePair("access_token", accessToken.getAccess_token()));
        try {
            String postJson = MAPPER.writeValueAsString(parameter);
            map.put("data", postJson);
            String response = HttpClientUtils.sendPost(HttpClientUtils.buildUrlWithParam(WechatConstant.WECHAT_DELETE_TAG_URL, list, WechatConstant.DEFAULT_CHARSET), map);
            APIResponse apiResponse = MAPPER.readValue(response, APIResponse.class);
            return apiResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public GetUsersOfTagResponse getUsersOfTag(long tagId, String next_openid) {
        if (tagId <= 0) {
            throw new RuntimeException("tagid must be greater than 0 ");
        }

        AccessTokenBean accessToken = accessTokenService.getAccessToken();
        if (accessToken == null) {
            accessToken = accessTokenService.refreshAccessToken();
        }
        GetUsersOfTagParameter getUsersOfTagParameter = new GetUsersOfTagParameter(tagId, next_openid);
        List<NameAndValuePair<String, String>> nameAndValuePairs = new ArrayList<>();
        nameAndValuePairs.add(new NameAndValuePair("access_token", accessToken.getAccess_token()));
        try {
            String postJson = MAPPER.writeValueAsString(getUsersOfTagParameter);
            String url = HttpClientUtils.buildUrlWithParam(WechatConstant.WECHAT_GET_USER_OF_TAG_URL, nameAndValuePairs, WechatConstant.DEFAULT_CHARSET);
            Map<String, String> map = new HashMap<>();
            map.put("data", postJson);
            String postResponse = HttpClientUtils.sendPost(url, map);
            GetUsersOfTagResponse getUsersOfTagResponse = MAPPER.readValue(postResponse, GetUsersOfTagResponse.class);
            return getUsersOfTagResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;

    }
}
