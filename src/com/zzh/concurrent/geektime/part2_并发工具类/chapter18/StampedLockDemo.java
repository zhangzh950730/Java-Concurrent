package com.zzh.concurrent.geektime.part2_并发工具类.chapter18;

import java.util.concurrent.locks.StampedLock;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/15 14:52
 */
public class StampedLockDemo {

    public static void main(String[] args) {

        final StampedLock sl = new StampedLock();
        // 获取/释放悲观读锁示意代码
        long stamp1 = sl.readLock();
        try {
            //省略业务相关代码
        } finally {
            sl.unlockRead(stamp1);
        }

        // 获取/释放写锁示意代码
        long stamp2 = sl.writeLock();
        try {
            //省略业务相关代码
        } finally {
            sl.unlockWrite(stamp2);
        }
    }
}
