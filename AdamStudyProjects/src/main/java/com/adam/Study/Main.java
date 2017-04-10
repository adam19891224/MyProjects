package com.adam.Study;

import java.math.BigDecimal;

/**
 * Created by zbj on 2017/4/10.
 */
public class Main {

    public static void main(String[] args){
        String a = "a";
        String b = "b";
        String c = a + b;

        String d = "a" + "b" + "c";

        StringBuilder sb1 = new StringBuilder("aaaaa");
        String sb2 = "sssss";
        sb1.append(sb2);
        System.out.println(c);

        Double bd = 1.12122333444555;
        BigDecimal bigDecimal = new BigDecimal(bd.toString());

        System.out.println(bigDecimal);
    }

    /**
     * 上面的反编译后如下：
     * public static void main(String[] args) {
             String a = "a";
             String b = "b";
             String c = a + b;
             String d = "abc";
             StringBuilder sb1 = new StringBuilder("aaaaa");
             String sb2 = "sssss";
             sb1.append(sb2);
             System.out.println(c);
       }
     */

}
