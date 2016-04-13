package com.foundation.utils;

import java.security.MessageDigest;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/21
 */
public class MD5Utils {

    private static final char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    public static String toMD5(String s) {

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

