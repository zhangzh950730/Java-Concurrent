package com.zzh.concurrent.Java并发编程实战.chapter8_线程池的使用;

import com.zzh.concurrent.Java并发编程实战.GuardedBy;
import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

import java.util.concurrent.CountDownLatch;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/14 16:13
 */
@ThreadSafe
public class ValueLatch<T> {
    @GuardedBy("this")
    private T value = null;
    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() {
        return (done.getCount() == 0);
    }

    public synchronized void setValue(T newValue) {
        if (!isSet()) {
            value = newValue;
            done.countDown();
        }
    }

    public T getValue() throws InterruptedException {
        done.await();
        synchronized (this) {
            return value;
        }
    }
}
