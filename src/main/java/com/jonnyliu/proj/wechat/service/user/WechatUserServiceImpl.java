package com.jonnyliu.proj.wechat.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.jonnyliu.proj.wechat.bean.*;
import com.jonnyliu.proj.wechat.constant.WechatConstant;
import com.jonnyliu.proj.wechat.service.AbstractWechatService;
import com.jonnyliu.proj.wechat.service.accesstoken.AccessTokenService;
import com.jonnyliu.proj.wechat.utils.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 微信用户服务实现
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-08-25 17:36.
 */
@Service
public class WechatUserServiceImpl extends AbstractWechatService implements WechatUserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatUserServiceImpl.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private AccessTokenService accessTokenService;

    @Override
    protected AccessTokenService getAccessTokenService() {
        return accessTokenService;
    }

    @Override
    public WechatUser getWechatUserInfo(GetUserInfoParameter userInfoParam) {
        Preconditions.checkNotNull(userInfoParam, " parameter is not allowed to be null or empty! ");
        AccessTokenBean accessToken = checkAccessToken();
        String url = WechatConstant.WECHAT_USER_FETCH_URL.replaceAll("ACCESS_TOKEN",accessToken.getAccess_token())
                .replaceAll("OPENID",userInfoParam.getOpenid())
                .replaceAll("LANG",userInfoParam.getLang());
        List<NameAndValuePair<String, String>> nameAndValuePairs = new ArrayList<>();
        WechatUser wechatUser = null;
        try {
            String response = HttpClientUtils.sendGet(url, nameAndValuePairs);
            wechatUser = MAPPER.readValue(response, WechatUser.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return wechatUser;
    }

    @Override
    public List<WechatUser> batchGetWechatUserInfo(BatchGetUserRequestParameter getUserParamList) {
        Preconditions.checkNotNull(getUserParamList, " parameter is not allowed to be null or empty! ");
        AccessTokenBean accessToken = checkAccessToken();
        try {
            String url = WechatConstant.WECHAT_USER_BATCH_FETCH_URL.replaceAll("ACCESS_TOKEN",accessToken.getAccess_token());
            String paramJson = MAPPER.writeValueAsString(getUserParamList);
            String postResponse = HttpClientUtils.sendPost(url, paramJson);
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
    public CreateWechatUserTagResponse createTag(String tagName) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(tagName), "parameter [tagName] is not allowed to be null or empty !");
        Preconditions.checkArgument(tagName.length() < WechatConstant.WECHAT_USER_TAG_LENGTH);
        AccessTokenBean accessToken = checkAccessToken();
        WechatUserTag tag = new WechatUserTag();
        tag.setName(tagName);
        CreateOrEditTagParameter parameter = new CreateOrEditTagParameter(tag);
        try {
            String url = WechatConstant.WECHAT_CREATE_TAG_URL.replaceAll("ACCESS_TOKEN",accessToken.getAccess_token());
            String json = MAPPER.writeValueAsString(parameter);
            String response = HttpClientUtils.sendPost(url, json);
            CreateWechatUserTagResponse createWechatUserTagResponse = MAPPER.readValue(response, CreateWechatUserTagResponse.class);
            return createWechatUserTagResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public GetWechatTagResponse getTags() {
        AccessTokenBean accessToken = checkAccessToken();
        try {
            String requestUrl = WechatConstant.WECHAT_GET_TAG_URL.replaceAll("ACCESS_TOKEN",accessToken.getAccess_token());
            String response = HttpClientUtils.sendGet(requestUrl);
            GetWechatTagResponse getWechatTagResponse = MAPPER.readValue(response, GetWechatTagResponse.class);
            return getWechatTagResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public APIResponse editTag(CreateOrEditTagParameter parameter) {
        Preconditions.checkNotNull(parameter, "no parameter passed !");
        AccessTokenBean accessToken = checkAccessToken();
        try {
            String url = WechatConstant.WECHAT_EDIT_TAG_URL.replaceAll("ACCESS_TOKEN",accessToken.getAccess_token());
            String postJson = MAPPER.writeValueAsString(parameter);
            String response = HttpClientUtils.sendPost(url, postJson);
            APIResponse apiResponse = MAPPER.readValue(response, APIResponse.class);
            return apiResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public APIResponse deleteTag(long tagId) {
        Preconditions.checkArgument(tagId > 0, "parameter tagid must be greater than 0");
        AccessTokenBean accessToken = checkAccessToken();
        WechatUserTag tag = new WechatUserTag();
        tag.setId(tagId);
        CreateOrEditTagParameter parameter = new CreateOrEditTagParameter(tag);
        try {
            String postJson = MAPPER.writeValueAsString(parameter);
            String url = WechatConstant.WECHAT_DELETE_TAG_URL.replaceAll("ACCESS_TOKEN",accessToken.getAccess_token());
            String response = HttpClientUtils.sendPost(url, postJson);
            APIResponse apiResponse = MAPPER.readValue(response, APIResponse.class);
            return apiResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public GetUsersOfTagResponse getUsersOfTag(long tagId, String next_openid) {
        Preconditions.checkArgument(tagId > 0, "parameter tagid must be greater than 0 ");
        AccessTokenBean accessToken = checkAccessToken();
        GetUsersOfTagParameter getUsersOfTagParameter = new GetUsersOfTagParameter(tagId, next_openid);
        try {
            String postJson = MAPPER.writeValueAsString(getUsersOfTagParameter);
            String url = WechatConstant.WECHAT_GET_USER_OF_TAG_URL.replaceAll("ACCESS_TOKEN",accessToken.getAccess_token());
            String postResponse = HttpClientUtils.sendPost(url, postJson);
            GetUsersOfTagResponse getUsersOfTagResponse = MAPPER.readValue(postResponse, GetUsersOfTagResponse.class);
            return getUsersOfTagResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public APIResponse batchTagUsers(long tagId, List<String> openid_list) {
        Preconditions.checkArgument(tagId > 0, "tagid must be greater than 0");
        Preconditions.checkArgument(openid_list != null && !openid_list.isEmpty(), "openid list is not allowed to be null or empty! ");
        AccessTokenBean accessToken = checkAccessToken();
        try {
            BatchTagUsersParameter batchTagUsersParameter = new BatchTagUsersParameter(tagId, openid_list.toArray(new String[]{}));
            String jsonParam = MAPPER.writeValueAsString(batchTagUsersParameter);
            String url = WechatConstant.WECHAT_BATCH_TAG_USER_URL.replaceAll("ACCESS_TOKEN", accessToken.getAccess_token());
            String postJson = HttpClientUtils.sendPost(url, jsonParam);
            APIResponse apiResponse = MAPPER.readValue(postJson, APIResponse.class);
            return apiResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public APIResponse batchUnTagUsers(long tagId, List<String> openid_list) {

        Preconditions.checkArgument(tagId > 0, "tagid must be greater than 0 ");
        Preconditions.checkArgument(openid_list != null && !openid_list.isEmpty(), "openid list is not allowed to be null or empty! ");
        AccessTokenBean accessToken = checkAccessToken();
        try {
            BatchTagUsersParameter batchTagUsersParameter = new BatchTagUsersParameter(tagId, openid_list.toArray(new String[]{}));
            String jsonParam = MAPPER.writeValueAsString(batchTagUsersParameter);
            String url = WechatConstant.WECHAT_BATCH_UNTAG_USER_URL.replaceAll("ACCESS_TOKEN", accessToken.getAccess_token());
            String postJson = HttpClientUtils.sendPost(url, jsonParam);
            APIResponse apiResponse = MAPPER.readValue(postJson, APIResponse.class);
            return apiResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public GetTagsOfUserResponse getTagsOfUser(String openId) {
        openId = Preconditions.checkNotNull(openId, "openId must not be null or empty! ");
        AccessTokenBean accessToken = checkAccessToken();
        try {
            GetTagsOfUserParameter parameter = new GetTagsOfUserParameter(openId);
            String jsonParam = MAPPER.writeValueAsString(parameter);
            String url = WechatConstant.WECHAT_GET_TAGS_OF_USER_URL.replaceAll("ACCESS_TOKEN", accessToken.getAccess_token());
            String postJson = HttpClientUtils.sendPost(url, jsonParam);
            GetTagsOfUserResponse apiResponse = MAPPER.readValue(postJson, GetTagsOfUserResponse.class);
            return apiResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public APIResponse remarkUser(String openId, String remark) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(openId), "parameter openid is not allowed to be null or empty !");
        Preconditions.checkArgument(StringUtils.isNotEmpty(remark), "parameter remark is not allowed to be null or empty !");
        Preconditions.checkArgument(remark.length() < WechatConstant.WECHAT_USER_REMARK_MAX_LENGTH, "parameter remark length to long,allowed max length : {},actual length : {}", WechatConstant.WECHAT_USER_REMARK_MAX_LENGTH, remark.length());
        AccessTokenBean accessToken = checkAccessToken();
        RemarkUserParameter remarkUserParameter = new RemarkUserParameter(openId, remark);
        try {
            String json = MAPPER.writeValueAsString(remarkUserParameter);
            String url = WechatConstant.WECHAT_REMARK_USER_URL.replaceAll("ACCESS_TOKEN", accessToken.getAccess_token());
            String postJson = HttpClientUtils.sendPost(url, json);
            APIResponse apiResponse = MAPPER.readValue(postJson, APIResponse.class);
            return apiResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public GetUserBlackListResponse getUserBlackList(String begin_openId) {
        AccessTokenBean accessToken = checkAccessToken();
        try {
            if (begin_openId == null) {
                begin_openId = "";
            }
            NameAndValuePair<String, String> nameAndValuePair = new NameAndValuePair<>("begin_openid", begin_openId);
            String url = WechatConstant.WECHAT_GET_BLACK_LIST_URL.replaceAll("ACCESS_TOKEN", accessToken.getAccess_token());
            String postJson = HttpClientUtils.sendPost(url, nameAndValuePair.toString());
            GetUserBlackListResponse getUserBlackListResponse = MAPPER.readValue(postJson, GetUserBlackListResponse.class);
            return getUserBlackListResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public APIResponse blackUser(List<String> openid_list) {
        Preconditions.checkArgument(openid_list != null && !openid_list.isEmpty(), "parameter openid_list is not allowed to be null or empty !");
        //一次拉黑最多允许20个
        Preconditions.checkArgument(openid_list.size() <= WechatConstant.WECHAT_BLACK_USER_MAX_SIZE, "over limit the max size of black user ,actural :{},limit :{}", openid_list.size(), WechatConstant.WECHAT_BLACK_USER_MAX_SIZE);

        AccessTokenBean accessToken = checkAccessToken();
        try {
            UserOpenIdList openIdList = new UserOpenIdList();
            openIdList.setOpenid(openid_list.toArray(new String[]{}));
            String url = WechatConstant.WECHAT_BLACK_USER_URL.replaceAll("ACCESS_TOKEN", accessToken.getAccess_token());
            String postJson = HttpClientUtils.sendPost(url, openIdList.toString());
            APIResponse apiResponse = MAPPER.readValue(postJson, APIResponse.class);
            return apiResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public APIResponse unblackUser(List<String> openid_list) {
        Preconditions.checkArgument(openid_list != null && !openid_list.isEmpty(), "parameter openid_list is not allowed to be null or empty !");
        //一次取消拉黑最多允许20个
        Preconditions.checkArgument(openid_list.size() <= WechatConstant.WECHAT_BLACK_USER_MAX_SIZE, "over limit the max size of black user ,actural :{},limit :{}", openid_list.size(), WechatConstant.WECHAT_BLACK_USER_MAX_SIZE);

        AccessTokenBean accessToken = checkAccessToken();
        try {
            UserOpenIdList openIdList = new UserOpenIdList();
            openIdList.setOpenid(openid_list.toArray(new String[]{}));
            String url = WechatConstant.WECHAT_UNBLACK_USER_URL.replaceAll("ACCESS_TOKEN", accessToken.getAccess_token());
            String postJson = HttpClientUtils.sendPost(url, openIdList.toString());
            APIResponse apiResponse = MAPPER.readValue(postJson, APIResponse.class);
            return apiResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
