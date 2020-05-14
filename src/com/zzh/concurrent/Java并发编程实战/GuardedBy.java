package com.zzh.concurrent.Java并发编程实战;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/12 17:53
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface GuardedBy {
    String value();
}
