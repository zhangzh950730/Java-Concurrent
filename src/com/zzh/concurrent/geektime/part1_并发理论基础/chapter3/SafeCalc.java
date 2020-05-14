package com.zzh.concurrent.geektime.part1_并发理论基础.chapter3;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/14 17:19
 */
public class SafeCalc {
    private long value = 0L;

    synchronized long get() {
        return value;
    }

    synchronized void addOne() {
        value += 1;
    }
}
