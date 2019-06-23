package com.ayyovei.aop.retry.annotation;

import java.lang.annotation.*;

/**
 * @author chenxiaobin
 * @description
 * @create 2019/6/23
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {


    /**
     * 最大失败尝试
     * @return
     */
    int maxRetry() default 3;
}
