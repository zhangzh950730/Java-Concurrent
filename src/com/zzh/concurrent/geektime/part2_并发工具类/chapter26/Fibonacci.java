package com.zzh.concurrent.geektime.part2_并发工具类.chapter26;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/17 9:48
 */
public class Fibonacci extends RecursiveTask<Integer> {
    final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        // 创建子任务
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        // 等待子任务结果，并合并结果
        return f2.compute() + f1.join();
    }

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool(4);
        Fibonacci fib = new Fibonacci(30);
        Integer result = fjp.invoke(fib);
        System.out.println("result = " + result);
    }
}
