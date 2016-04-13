package com.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防止重复提交
 * 使用方法：
 * 在需要生成token的controller链接上面加上注解@SubmitToken(save == true)
 * 在需要验证是否重复提交的controller链接上加上注解@SubmitToken(remove == ture)
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SubmitToken {

    /**
     * 该方法表示是否需要保存一个token到session中，用于进入提交页面，
     */
    boolean save() default false;

    /**
     * 该方法表示是否需要移除现有session中的token，用于验证提交，提交通过，就移除，那么当用户通过
     * 回退按钮，提交时，session中的token已经没有了，就会验证失败
     */
    boolean remove() default false;
}
