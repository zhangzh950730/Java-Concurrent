package com.zzh.concurrent.geektime.part3_并发设计模式.chapter35;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/18 10:49
 */
public class Proxy {
    private boolean started = false;
    // 采集线程
    private Thread rptThread;
    private volatile boolean terminated = false;

    /**
     * 启动采集功能
     */
    synchronized void start() {
        // 不允许同时启动多个采集线程
        if (started) {
            return;
        }
        started = true;
        terminated = false;

        rptThread = new Thread(() -> {
            while (!terminated) {
                // 省略采集、回传实现
                report();
                // 每隔两秒钟采集、回传一次数据
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            // 执行到此处说明线程马上终止
            started = false;
        });
        rptThread.start();
    }

    private void report() {

    }

    /**
     * 终止采集功能
     */
    synchronized void stop() {
        // 设置中断标志位
        terminated = true;
        // 中断线程rptThread
        rptThread.interrupt();
    }
}
