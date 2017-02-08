package com.enjoylife.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Adam
 * 2016/7/29
 */
public class DateUtils {

    public static String castDate2YMDString(Date date){

        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return localDateTime.toLocalDate().toString();
    }

}
