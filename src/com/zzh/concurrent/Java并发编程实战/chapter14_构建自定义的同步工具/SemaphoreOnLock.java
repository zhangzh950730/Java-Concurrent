package com.zzh.concurrent.Java并发编程实战.chapter14_构建自定义的同步工具;

import com.zzh.concurrent.Java并发编程实战.GuardedBy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 16:02
 */
public class SemaphoreOnLock {
    private final Lock lock = new ReentrantLock();
    private final Condition permitsAvailable = lock.newCondition();
    @GuardedBy("this")
    private int permits;

    SemaphoreOnLock(int initialPermits) {
        lock.lock();
        try {
            permits = initialPermits;
        } finally {
            lock.unlock();
        }
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits <= 0) {
                permitsAvailable.await();
            }
            permits--;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            permits++;
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }
}
