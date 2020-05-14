package com.zzh.concurrent.Java并发编程实战.chapter8_线程池的使用;

import java.util.concurrent.ThreadFactory;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/14 14:52
 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new MyAppThread(runnable, poolName);
    }
}
