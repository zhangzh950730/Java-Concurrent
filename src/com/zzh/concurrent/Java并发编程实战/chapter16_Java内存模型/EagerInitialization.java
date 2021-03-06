package com.zzh.concurrent.Java并发编程实战.chapter16_Java内存模型;

import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 19:21
 */
@ThreadSafe
public class EagerInitialization {
    private static Resource resource = new Resource();

    public static Resource getInstance() {
        return resource;
    }
}
