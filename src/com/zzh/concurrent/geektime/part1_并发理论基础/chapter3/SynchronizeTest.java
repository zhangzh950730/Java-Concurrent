package com.zzh.concurrent.geektime.part1_并发理论基础.chapter3;

/**
 * 演示 synchronized关键字的用法
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/14 16:58
 */
public class SynchronizeTest {

    // 修饰非静态方法
    synchronized void foo() {
        // 临界区
    }

    // 修饰静态方法
    synchronized static void bar() {
        // 临界区
    }

    void baz() {
        // 修饰代码块
        Object o = new Object();
        synchronized (o) {
            // 临界区
        }
    }


}
