package com.zzh.concurrent.geektime.part2_并发工具类.chapter23;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/16 15:39
 */
public class FutureTask1 implements Callable<String> {
    private FutureTask<String> ft2;

    public FutureTask1(FutureTask<String> ft2) {
        this.ft2 = ft2;
    }

    @Override
    public String call() throws Exception {
        System.out.println("T1 洗水壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T1 烧开水");
        TimeUnit.SECONDS.sleep(15);

        String ft2Result = ft2.get();
        System.out.println("T1 拿到茶叶 + " + ft2Result);

        System.out.println("T1 泡茶");

        return "上茶 " + ft2Result;
    }
}
