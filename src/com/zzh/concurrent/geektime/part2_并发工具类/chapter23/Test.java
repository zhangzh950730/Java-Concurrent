package com.zzh.concurrent.geektime.part2_并发工具类.chapter23;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/16 15:21
 */
public class Test {
    private static int a = 6;
    private static int b = 7;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Result result = new Result();
        Future<Result> future = executorService.submit(new Task(result), result);
        System.out.println("result = " + result);

        try {
            Result result1 = future.get();
            System.out.println("result1 = " + result1);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class Task implements Runnable {
        private Result result;

        public Task(Result result) {
            this.result = result;
        }

        @Override
        public void run() {
            result.setA(a);
            result.setB(b);
        }
    }
}


