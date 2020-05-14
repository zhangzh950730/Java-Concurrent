package com.zzh.concurrent.geektime.part2_并发工具类.chapter15;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/15 11:01
 */
public class DefaultFuture {

    private Response response;

    // 创建锁与条件变量
    private final Lock lock = new ReentrantLock();
    private final Condition done = lock.newCondition();

    // 调用方通过该方法等待结果
    Object get(int timeout) throws TimeoutException {
        long start = System.nanoTime();
        lock.lock();
        try {
            while (!isDone()) {
                done.await(timeout, TimeUnit.SECONDS);
                long cur = System.nanoTime();
                if (isDone() || cur - start > timeout) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        if (!isDone()) {
            throw new TimeoutException();
        }
        return returnFromResponse();
    }

    private Object returnFromResponse() {
        return response;
    }

    // RPC结果是否已经返回
    private boolean isDone() {
        return response != null;
    }

    // RPC结果返回时调用该方法
    private void doReceived(Response res) {
        lock.lock();
        try {
            response = res;
            done.signal();
        } finally {
            lock.unlock();
        }
    }
}
