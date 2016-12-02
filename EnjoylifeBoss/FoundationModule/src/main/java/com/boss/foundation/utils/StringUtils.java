package com.boss.foundation.utils;

/**
 * ranmin-zhouyuhong
 * 2016/12/2
 */
public class StringUtils {

    public static boolean isNotBlank(String str) {
        int length;
        if(str != null && (length = str.length()) != 0) {
            for(int i = 0; i < length; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

}
