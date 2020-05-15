package com.zzh.concurrent.Java并发编程实战.chapter16_Java内存模型;

import com.zzh.concurrent.Java并发编程实战.NotThreadSafe;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 19:19
 */
@NotThreadSafe
public class UnSafeLazyInitialization {
    private static Resource resource;

    public static Resource getInstance() {
        if (resource == null) {
            resource = new Resource();
        }
        return resource;
    }
}
