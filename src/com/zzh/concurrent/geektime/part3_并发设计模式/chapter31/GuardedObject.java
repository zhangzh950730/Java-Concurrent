package com.zzh.concurrent.geektime.part3_并发设计模式.chapter31;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/17 17:05
 */
public class GuardedObject<T> {
    private T obj;
    private final Lock lock = new ReentrantLock();
    private final Condition done = lock.newCondition();
    private final int timeout = 2;

    //保存所有GuardedObject
    private final static Map<Object, GuardedObject> gos = new ConcurrentHashMap<>();

    /**
     * 静态方法创建 GuardedObject
     */
    public static <K> GuardedObject create(K key) {
        GuardedObject go = new GuardedObject<>();
        gos.put(key, go);
        return go;
    }

    private static <K, T> void fireEvent(K key, T obj) {
        GuardedObject go = gos.remove(key);
        if (go != null) {
            go.onChanged(obj);
        }
    }

    /**
     * 获取受保护的对象
     */
    public T get(Predicate<T> p) {
        lock.lock();
        try {
            // MESA管程推荐写法
            while (!p.test(obj)) {
                done.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        // 返回非空的受保护对象
        return obj;
    }

    /**
     * 事件通知方法
     */
    private void onChanged(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }


}
