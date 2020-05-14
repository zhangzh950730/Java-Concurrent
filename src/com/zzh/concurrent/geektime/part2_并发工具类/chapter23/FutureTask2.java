package com.zzh.concurrent.geektime.part2_并发工具类.chapter23;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/16 15:40
 */
public class FutureTask2 implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("T2 洗茶壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T2 洗茶杯");
        TimeUnit.SECONDS.sleep(2);

        System.out.println("T2 拿茶叶");
        TimeUnit.SECONDS.sleep(1);

        return "龙井";
    }
}
