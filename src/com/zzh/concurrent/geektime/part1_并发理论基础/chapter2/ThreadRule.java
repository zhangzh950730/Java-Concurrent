package com.zzh.concurrent.geektime.part1_并发理论基础.chapter2;

/**
 * Happens-Before中的线程规则
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/14 16:34
 */
public class ThreadRule {
    private int var = 0;

    /**
     * 线程start()规则
     */
    public void start() {
        Thread B = new Thread(() -> {
            // 主线程调用B.start()之前
            // 所有对共享变量的修改，此处皆可见
            // 此例中，var==77
            System.out.println("var = " + var);
        });
        // 此处对共享变量var修改
        var = 77;
        // 主线程启动子线程
        B.start();
    }

    /**
     * 线程join()规则
     */
    public void join() {
        System.out.println("var start = " + var);
        Thread B = new Thread(() -> {
            // 此处对共享变量var修改
            System.out.println("var B before = " + var);
            var = 66;
            System.out.println("var B after = " + var);
        });
        var = 77;
        // 例如此处对共享变量修改
        // 则这个修改结果对于线程B可见
        // 主线程启动子线程
        B.start();
        try {
            B.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 子线程所有对共享变量的修改
        // 主线程调用B.join()之后皆可见
        // 此例中，var = 66
        System.out.println("var = " + var);
    }

    public static void main(String[] args) {
        ThreadRule threadRule = new ThreadRule();
        threadRule.join();
    }
}
