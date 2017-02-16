package com.jonnyliu.proj.wechat.service.user;

import com.jonnyliu.proj.wechat.bean.*;
import com.jonnyliu.proj.wechat.enums.Lang;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用户管理的测试用例
 * author:980463316@qq.com <br/>
 * Created on 2016-08-28 14:25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class WechatUserServiceTest {

    @Autowired
    private WechatUserService wechatUserService;


    @Test
    public void testBatchGetUser() {
        BatchGetUserRequestParameter param = new BatchGetUserRequestParameter();
        GetUserInfoParameter userInfoParam = new GetUserInfoParameter("oHcaSt7ptU9LjEGQTjEAz7hKGdeU", Lang.CHINESE.getLanguageCode());

        GetUserInfoParameter userInfoParam2 = new GetUserInfoParameter("oHcaSt8rSz8GGjgiTRlL-PxBASMc", Lang.CHINESE.getLanguageCode());

        List<GetUserInfoParameter> list = new ArrayList<>(2);
        list.add(userInfoParam);
        list.add(userInfoParam2);
        param.setUser_list(list);
        List<WechatUser> wechatUsers = wechatUserService.batchGetWechatUserInfo(param);
        Assert.assertNotNull(wechatUsers);
        Assert.assertFalse(wechatUsers.isEmpty());
        Assert.assertTrue(wechatUsers.size() == 2);
    }

    @Test
    public void testCreateTag() {
        String random = RandomStringUtils.random(4);
        CreateWechatUserTagResponse createWechatUserTagResponse = wechatUserService.createTag(random);
        Assert.assertNotNull(createWechatUserTagResponse);
        Assert.assertNotNull(createWechatUserTagResponse.getTag());
        Assert.assertTrue(createWechatUserTagResponse.getTag().getId() > 0);
    }

    @Test
    public void testGetTags() {
        GetWechatTagResponse tags = wechatUserService.getTags();
        Assert.assertNotNull(tags);
        Assert.assertNotNull(tags.getTags());
        Assert.assertTrue(tags.getTags().size() >= 1);
    }

    @Test
    public void testEditTag() {
        WechatUserTag tag = new WechatUserTag(100, "test1");
        CreateOrEditTagParameter parameter = new CreateOrEditTagParameter(tag);
        APIResponse apiResponse = wechatUserService.editTag(parameter);
        Assert.assertNotNull(apiResponse);
        Assert.assertTrue(apiResponse.getErrcode() == 0);
        Assert.assertTrue(apiResponse.getErrmsg().equalsIgnoreCase("ok"));
    }

    @Test
    public void testDeleteTag() {
        APIResponse apiResponse = wechatUserService.deleteTag(100);
        Assert.assertNotNull(apiResponse);
        Assert.assertTrue(apiResponse.getErrcode() == 0);
        Assert.assertTrue(apiResponse.getErrmsg().equalsIgnoreCase("ok"));
    }

    @Test
    public void testGetUsersOfTag() {
        GetUsersOfTagResponse usersOfTag = wechatUserService.getUsersOfTag(1, null);
        Assert.assertNotNull(usersOfTag);
        Assert.assertTrue(usersOfTag.getCount() > 0);
    }

    @Test
    public void testBatchTagUsers() {
        APIResponse apiResponse = wechatUserService.batchTagUsers(2, Arrays.asList("oHcaSt095yszSEw3dDSPBHpePfXo", "oHcaSt9zbSPMQijngGJPLK4Yy8iA"));
        Assert.assertNotNull(apiResponse);
        Assert.assertTrue(apiResponse.getErrcode() == 0);
        Assert.assertTrue(apiResponse.getErrmsg().equalsIgnoreCase("ok"));
    }

    @Test
    public void testBatchUnTagUsers() {
        APIResponse apiResponse = wechatUserService.batchUnTagUsers(2, Arrays.asList("oHcaSt095yszSEw3dDSPBHpePfXo", "oHcaSt9zbSPMQijngGJPLK4Yy8iA"));
        Assert.assertNotNull(apiResponse);
        Assert.assertTrue(apiResponse.getErrcode() == 0);
        Assert.assertTrue(apiResponse.getErrmsg().equalsIgnoreCase("ok"));
    }

    @Test
    public void testGetTagsOfUser() {
        GetTagsOfUserResponse tagsOfUser = wechatUserService.getTagsOfUser("oHcaSt7ptU9LjEGQTjEAz7hKGdeU");
        Assert.assertNotNull(tagsOfUser);
        Assert.assertNotNull(tagsOfUser.getTagid_list());
    }

    @Test
    public void testRemarkUser() {
        APIResponse apiResponse = wechatUserService.remarkUser("oHcaSt7ptU9LjEGQTjEAz7hKGdeU", "二货");
        Assert.assertNotNull(apiResponse);
        Assert.assertTrue(apiResponse.getErrcode() == 0);
        Assert.assertTrue(apiResponse.getErrmsg().equalsIgnoreCase("ok"));
    }

    @Test
    public void testGetBlackList() {
        GetUserBlackListResponse userBlackList = wechatUserService.getUserBlackList(null);
        Assert.assertNotNull(userBlackList);
    }

    @Test
    public void testBlackUser() {
        APIResponse apiResponse = wechatUserService.blackUser(Arrays.asList("oHcaSt7ptU9LjEGQTjEAz7hKGdeU"));
        Assert.assertNotNull(apiResponse);
        Assert.assertTrue(apiResponse.getErrcode() == 0);
        Assert.assertTrue(apiResponse.getErrmsg().equalsIgnoreCase("ok"));
    }

    @Test
    public void testUnBlackUser() {
        APIResponse apiResponse = wechatUserService.unblackUser(Arrays.asList("oHcaSt7ptU9LjEGQTjEAz7hKGdeU"));
        Assert.assertNotNull(apiResponse);
        Assert.assertTrue(apiResponse.getErrcode() == 0);
        Assert.assertTrue(apiResponse.getErrmsg().equalsIgnoreCase("ok"));
    }
}
