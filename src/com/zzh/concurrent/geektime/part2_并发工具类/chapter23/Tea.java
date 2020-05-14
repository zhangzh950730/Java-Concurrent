package com.zzh.concurrent.geektime.part2_并发工具类.chapter23;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/16 15:46
 */
public class Tea {
    public static void main(String[] args) {
        FutureTask<String> ft2 = new FutureTask<>(new FutureTask2());
        FutureTask<String> ft1 = new FutureTask<>(new FutureTask1(ft2));

        Thread thread1 = new Thread(ft1);
        thread1.start();

        Thread thread2 = new Thread(ft2);
        thread2.start();

        try {
            System.out.println(ft1.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
