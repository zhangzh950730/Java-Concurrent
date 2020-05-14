package com.zzh.concurrent.geektime.part1_并发理论基础.chapter1;

/**
 * 验证多个线程并发对共享变量操作时，CPU缓存导致的数据可见性问题
 * 多个线程同时执行的时候，将count=0读入各自的CPU缓存，各自执行完count+1后，各自缓存中的值都为1，
 * 又同时写入内存，此时内存中count应为2实际却是1
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/14 15:34
 */
public class Test1 {
    private long count = 0;

    private void add10k() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    public long calc() {
        // 创建两个线程，执行add10k操作
        Thread thread1 = new Thread(this::add10k);
        Thread thread2 = new Thread(this::add10k);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.count;
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        long count = test1.calc();
        System.out.println("test1.count = " + count);
    }
}
