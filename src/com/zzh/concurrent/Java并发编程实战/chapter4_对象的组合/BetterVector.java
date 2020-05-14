package com.zzh.concurrent.Java并发编程实战.chapter4_对象的组合;

import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

import java.util.Vector;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/13 10:50
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;
    }
}
