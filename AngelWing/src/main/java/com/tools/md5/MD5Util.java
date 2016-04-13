package com.tools.md5;

import org.apache.log4j.Logger;

import java.security.MessageDigest;

public class MD5Util {

    protected static Logger logger = Logger.getLogger("sysAppender");

	/*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5;
        try{  
            md5 = MessageDigest.getInstance("MD5");
            char[] charArray = inStr.toCharArray();
            byte[] byteArray = new byte[charArray.length];

            for (int i = 0; i < charArray.length; byteArray[i++] = (byte) charArray[i++]);

            byte[] md5Bytes = md5.digest(byteArray);

            StringBuilder hexValue = new StringBuilder();

            for (byte md5Byte : md5Bytes) {

                int val = ((int) md5Byte) & 0xff;

                if (val < 16)
                    hexValue.append("0");

                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        }catch (Exception e){
            logger.error(e);
        }
        return "";
    }
	
}
