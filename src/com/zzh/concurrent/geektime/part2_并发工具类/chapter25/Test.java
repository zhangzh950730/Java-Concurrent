package com.zzh.concurrent.geektime.part2_并发工具类.chapter25;

import java.util.concurrent.*;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/16 17:14
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletionService<Integer> cs = new ExecutorCompletionService<>(executorService);

        cs.submit(() -> getPriceByS1());
        cs.submit(() -> getPriceByS2());
        cs.submit(() -> getPriceByS3());

        for (int i = 0; i < 3; i++) {
            Integer r = null;
            try {
                r = cs.take().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            Integer finalR = r;
            executorService.execute(() -> {
                System.out.println("save：" + finalR);
            });

        }
    }


    private static Integer getPriceByS3() {
        return 3;
    }

    private static Integer getPriceByS2() {
        return 2;
    }

    private static Integer getPriceByS1() {
        return 1;
    }
}
