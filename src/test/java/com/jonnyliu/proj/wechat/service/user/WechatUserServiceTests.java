package com.jonnyliu.proj.wechat.service.user;

import com.jonnyliu.proj.wechat.bean.BatchGetUserRequestParam;
import com.jonnyliu.proj.wechat.bean.CreateTagResponse;
import com.jonnyliu.proj.wechat.bean.GetUserInfoParam;
import com.jonnyliu.proj.wechat.bean.WechatUser;
import com.jonnyliu.proj.wechat.enums.Lang;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理的测试用例
 * author:980463316@qq.com <br/>
 * Created on 2016-08-28 14:25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class WechatUserServiceTests {

    @Autowired
    private WechatUserService wechatUserService;


    @Test
    public void testBatchGetUser() {
        BatchGetUserRequestParam param = new BatchGetUserRequestParam();
        GetUserInfoParam userInfoParam = new GetUserInfoParam("oHcaSt095yszSEw3dDSPBHpePfXo", Lang.CHINESE.getLanguageCode());

        GetUserInfoParam userInfoParam2 = new GetUserInfoParam("oHcaSt8rSz8GGjgiTRlL-PxBASMc", Lang.CHINESE.getLanguageCode());

        List<GetUserInfoParam> list = new ArrayList<>(2);
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
        CreateTagResponse createTagResponse = wechatUserService.createTag("test");
        Assert.assertNotNull(createTagResponse);
        Assert.assertNotNull(createTagResponse.getTag());
        Assert.assertTrue(createTagResponse.getTag().getId() > 0);
    }

}
