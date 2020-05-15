package com.zzh.concurrent.Java并发编程实战.chapter16_Java内存模型;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 18:09
 */
public class PossibleReordeering {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            a = 1;
            x = b;
        });

        Thread other = new Thread(() -> {
            b = 1;
            y = a;
        });

        one.start();
        other.start();
        one.join();
        other.join();
        System.out.println("(" + x + "," + y + ")");

    }
}
