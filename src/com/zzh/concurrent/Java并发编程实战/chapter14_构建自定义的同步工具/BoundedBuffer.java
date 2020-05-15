package com.zzh.concurrent.Java并发编程实战.chapter14_构建自定义的同步工具;

import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 14:42
 */
@ThreadSafe
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

    protected BoundedBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        doPut(v);
        notifyAll();
    }

    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }
}
