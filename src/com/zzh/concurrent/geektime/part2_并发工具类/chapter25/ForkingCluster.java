package com.zzh.concurrent.geektime.part2_并发工具类.chapter25;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/16 17:25
 */
public class ForkingCluster {

    public static void main(String[] args) {
        Integer r = demo();
        System.out.println(r);
    }

    private static Integer demo() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        List<Future<Integer>> futures = new ArrayList<>();

        futures.add(cs.submit(() -> geocoderByS1()));
        futures.add(cs.submit(() -> geocoderByS2()));
        futures.add(cs.submit(() -> geocoderByS3()));

        Integer r = 0;
        try {
            for (int i = 0; i < 3; i++) {
                try {
                    r = cs.take().get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                if (r != null) {
                    break;
                }

            }
        } finally {
            for (Future<Integer> future : futures) {
                future.cancel(true);
            }
        }
        return r;
    }

    private static Integer geocoderByS1() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private static Integer geocoderByS2() {
        return 2;
    }

    private static Integer geocoderByS3() {
        return 3;
    }
}
