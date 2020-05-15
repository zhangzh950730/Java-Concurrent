package com.zzh.concurrent.Java并发编程实战.chapter16_Java内存模型;

import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

/**
 * 延迟初始化占位类模式
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 19:23
 */
@ThreadSafe
public class ResourceFactory {
    private static class ResourceHolder {
        public static Resource resource = new Resource();
    }

    public static Resource getInstance() {
        return ResourceHolder.resource;
    }
}
