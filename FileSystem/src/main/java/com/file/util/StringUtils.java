package com.file.util;

/**
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/25
 */
class StringUtils {

    static boolean isNotNull(String s){
        return s != null && !s.trim().equalsIgnoreCase("") && s.length() > 0;
    }

    static boolean isEqual(String str1, String str2){
        return str1.length() == str2.length() && str1.equalsIgnoreCase(str2);
    }
}
