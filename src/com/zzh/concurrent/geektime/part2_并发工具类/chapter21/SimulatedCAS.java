package com.zzh.concurrent.geektime.part2_并发工具类.chapter21;

class SimulatedCAS {
    private int count;

    synchronized int cas(int expect, int newValue) {
        // 读目前count的值
        int curValue = count;
        // 比较目前count值是否==期望值
        if (curValue == expect) {
            // 如果是，则更新count的值
            count = newValue;
        }
        // 返回写入前的值
        return curValue;
    }
}