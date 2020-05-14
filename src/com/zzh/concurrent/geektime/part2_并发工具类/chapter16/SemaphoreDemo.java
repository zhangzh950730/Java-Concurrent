package com.zzh.concurrent.geektime.part2_并发工具类.chapter16;

import java.util.concurrent.Semaphore;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/15 11:16
 */
public class SemaphoreDemo {

    static int count;
    //初始化信号量
    static final Semaphore s
            = new Semaphore(1);

    //用信号量保证互斥
    static void addOne() throws InterruptedException {
        s.acquire();
        try {
            count += 1;
        } finally {
            s.release();
        }
    }
}
