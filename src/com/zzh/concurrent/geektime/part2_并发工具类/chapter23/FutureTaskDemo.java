package com.zzh.concurrent.geektime.part2_并发工具类.chapter23;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/16 15:30
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        demo1();
        System.out.println("=====");
        demo2();
    }

    private static void demo1() {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 2);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(futureTask);
        Integer result = null;
        try {
            result = futureTask.get();
            System.out.println("result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void demo2() {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 2);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Integer result = futureTask.get();
            System.out.println("result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
