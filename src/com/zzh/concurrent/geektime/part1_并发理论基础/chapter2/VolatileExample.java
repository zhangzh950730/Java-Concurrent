package com.zzh.concurrent.geektime.part1_并发理论基础.chapter2;

/**
 * 线程A调用writer方法，线程B调用reader方法。
 * 根据Happens-Before
 * 1.（顺序性）x=42可见于写变量v
 * 2.（volatile变量的写对后续的读可见）写变量v可见于读变量v
 * 3.（传递性）x=42可见于reader方法中
 * 因此，reader中x的值也为42。
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/14 16:18
 */
public class VolatileExample {
    private int x = 0;
    private volatile boolean v = false;

    public void writer() {
        x = 42;
        v = true;
    }

    public void reader() {
        if (v) {
            //这里x回事多少呢？
            System.out.println("x = " + x);
        }
    }

    public static void main(String[] args) {
        VolatileExample volatileExample = new VolatileExample();
        Thread A = new Thread(volatileExample::writer);
        Thread B = new Thread(volatileExample::reader);
        A.start();
        B.start();
        try {
            A.join();
            B.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
