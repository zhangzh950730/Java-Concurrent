package com.zzh.concurrent.geektime.part2_并发工具类.chapter21;

import java.util.concurrent.atomic.AtomicReference;

public class SafeWM {
    static class WMRange {
        int upper;
        int lower;

        WMRange(int upper, int lower) {
            //省略构造函数实现
        }
    }

    private final AtomicReference<WMRange> rf = new AtomicReference<>(new WMRange(0, 0));

    // 设置库存上限
    void setUpper(int v) {
        WMRange or;
        WMRange nr;
        do {
            or = rf.get();
            // 检查参数合法性
            if (v < or.lower) {
                throw new IllegalArgumentException();
            }
            nr = new WMRange(v, or.lower);
        } while (!rf.compareAndSet(or, nr));
    }
}