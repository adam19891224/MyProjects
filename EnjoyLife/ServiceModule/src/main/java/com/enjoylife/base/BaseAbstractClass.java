package com.enjoylife.base;

import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/5/3
 */
public abstract class BaseAbstractClass {

    protected Logger logger = Logger.getLogger(this.getClass());

    protected long getMsBetweenTwoDate(Date start, Date end){
        return end.getTime() - start.getTime();
    }
}
