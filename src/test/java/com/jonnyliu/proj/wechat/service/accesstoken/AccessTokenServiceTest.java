package com.jonnyliu.proj.wechat.service.accesstoken;

import com.jonnyliu.proj.wechat.bean.AccessTokenBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <p/>
 * User: jonnyliu@tcl.com <br/>
 * Date: on 2016-09-01 9:33.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class AccessTokenServiceTest {

    @Autowired
    private AccessTokenService accessTokenService;

    @Test
    public void testGetAccessToken() throws InterruptedException {

        while (true) {
            AccessTokenBean accessToken = accessTokenService.getAccessToken();
            System.out.println(accessToken);

            System.out.println("===================================");
            TimeUnit.SECONDS.sleep(10);
        }
    }

    /**
     * 测试多线程环境下access token的获取
     */
    @Test
    public void testGetAccessTokenInMutilThreadEnvironment() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new FetchAccessTokenThread("线程" + (i + 1), accessTokenService, latch));
            thread.start();
        }
        latch.await();
    }


    @Test
    public void testRefreshAccessToken() {
        AccessTokenBean accessToken = accessTokenService.refreshAccessToken();
        System.out.println(accessToken.getAccess_token());
    }

}
