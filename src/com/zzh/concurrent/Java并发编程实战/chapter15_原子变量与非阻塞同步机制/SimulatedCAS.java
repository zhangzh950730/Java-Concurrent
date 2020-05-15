package com.zzh.concurrent.Java并发编程实战.chapter15_原子变量与非阻塞同步机制;

import com.zzh.concurrent.Java并发编程实战.GuardedBy;
import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 16:58
 */
@ThreadSafe
public class SimulatedCAS {
    @GuardedBy("this")
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAnsSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAnsSet(int expectedValue, int newValue) {
        return expectedValue == compareAnsSwap(expectedValue, newValue);
    }
}
