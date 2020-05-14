package com.zzh.concurrent.geektime.part2_并发工具类.chapter16;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

class ObjPool<T, R> {
    private final List<T> pool;
    // 用信号量实现限流器
    private final Semaphore sem;

    // 构造函数
    private ObjPool(Integer size, T t) {
        pool = new Vector<>();
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        sem = new Semaphore(size);
    }

    // 利用对象池的对象，调用func
    private R exec(Function<T, R> func) {
        T t = null;
        try {
            sem.acquire();
            t = pool.remove(0);
            return func.apply(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.add(t);
            sem.release();
        }
        return null;
    }

    public static void main(String[] args) {
        // 创建对象池
        ObjPool<Integer, String> pool = new ObjPool<>(10, 2);
        // 通过对象池获取t，之后执行
        String result = pool.exec(t -> {
            System.out.println(t);
            return t.toString();
        });
        System.out.println("result = " + result);
    }
}
