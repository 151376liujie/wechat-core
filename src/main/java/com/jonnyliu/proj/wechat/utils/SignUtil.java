package com.jonnyliu.proj.wechat.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 请求校验工具类
 * author : 980463316@qq.com <br/>
 * Created on 2016/08/09 15:38.
 */
public class SignUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignUtil.class);

    private static char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * 验证签名
     *
     * @param token token,用户自定义的一个字符串
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return 合法则返回true;不合法就返回false
     */
    public static boolean checkSignature(String token,String signature, String timestamp, String nonce) {
        String[] arr = new String[] { token, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
}
