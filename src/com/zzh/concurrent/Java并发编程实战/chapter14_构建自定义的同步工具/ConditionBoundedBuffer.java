package com.zzh.concurrent.Java并发编程实战.chapter14_构建自定义的同步工具;

import com.zzh.concurrent.Java并发编程实战.GuardedBy;
import com.zzh.concurrent.Java并发编程实战.ThreadSafe;
import org.omg.CORBA.Object;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 15:48
 */
@ThreadSafe
public class ConditionBoundedBuffer<T> {
    protected final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    @GuardedBy("lock")
    private final T[] items = (T[]) new Object[16];
    @GuardedBy("lock")
    private int tail, head, count;

    public void put(T x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[tail] = x;
            if (++tail == items.length) {
                tail = 0;
            }
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            T x = items[head];
            items[head] = null;
            if (++head == items.length) {
                head = 0;
            }
            count--;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
