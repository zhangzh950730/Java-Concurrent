package com.zzh.concurrent.Java并发编程实战.chapter15_原子变量与非阻塞同步机制;

import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 17:02
 */
@ThreadSafe
public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (v != value.compareAnsSwap(v, v + 1));
        return v + 1;
    }
}
