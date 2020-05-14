package com.zzh.concurrent.geektime.part2_并发工具类.chapter21;

import java.util.concurrent.atomic.AtomicLong;

public class Test {
    private AtomicLong count = new AtomicLong(0);

    void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count.getAndIncrement();
        }
    }
}