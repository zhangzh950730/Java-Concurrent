package com.zzh.concurrent.Java并发编程实战.chapter4_对象的组合;

import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @since 2020/5/13 10:53
 */
/*@NotThreadSafe
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    */

/**
 * 非线程安全的 "若没有则添加" list使用的锁并不是ListHelper的锁
 * @param x
 * @return
 *//*
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }
}*/

@ThreadSafe
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
}
