package com.zzh.concurrent.geektime.part2_并发工具类.chapter17;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache<K, V> {
    private final Map<K, V> m = new HashMap<>();
    private final ReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();
    private volatile boolean cacheValid;

    V get(K key) {
        V v = null;
        //读缓存
        r.lock();
        try {
            v = m.get(key);
        } finally {
            r.unlock();
        }
        //缓存中存在，返回
        if (v != null) {
            return v;
        }
        //缓存中不存在，查询数据库
        w.lock();
        try {
            //再次验证
            //其他线程可能已经查询过数据库
            v = m.get(key);
            if (v == null) {
                //查询数据库
                //v=省略代码无数
                m.put(key, v);
            }
        } finally {
            w.unlock();
        }
        return v;
    }

    // 写缓存
    V put(K key, V value) {
        w.lock();
        try {
            return m.put(key, value);
        } finally {
            w.unlock();
        }
    }


    void processCachedData() {
        // 获取读锁
        r.lock();
        if (!cacheValid) {
            // 释放读锁，因为不允许读锁的升级
            r.unlock();
            // 获取写锁
            w.lock();
            try {
                // 再次检查状态
                if (!cacheValid) {
                    //data = ...
                    cacheValid = true;
                }
                // 释放写锁前，降级为读锁
                // 降级是可以的
                r.lock();
            } finally {
                // 释放写锁
                w.unlock();
            }
        }
        // 此处仍然持有读锁
        try {
            //use(data);
        } finally {
            r.unlock();
        }
    }

}