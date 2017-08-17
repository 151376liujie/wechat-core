package com.jonnyliu.proj.wechat;

import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.util.Map;

/**
 * Author: jonny
 * Time: 2017-08-16 22:07.
 */
public class BeanTest {


    @Test
    public void testMap2Bean() throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        map.put("Username", "jonnyliu");
        map.put("Age", 39);
        User user = new User();
        Map map1 = Maps.newHashMap();
        map.forEach((x, y) -> {
            char[] charArray = x.toCharArray();
            charArray[0] = Character.toLowerCase(charArray[0]);
            map1.put(new String(charArray), y);
        });
        BeanUtils.populate(user, map1);

        System.out.println(user);
    }
}
