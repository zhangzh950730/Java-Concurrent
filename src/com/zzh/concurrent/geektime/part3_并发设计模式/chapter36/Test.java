package com.zzh.concurrent.geektime.part3_并发设计模式.chapter36;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/18 14:40
 */
public class Test {

    // 任务队列
    BlockingQueue<Task> bq = new LinkedBlockingQueue<>(2000);

    /**
     * 启动5个消费者线程
     * 批量执行任务
     */
    void start() {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            es.execute(() -> {
                while (true) {
                    // 获取批量任务
                    List<Task> ts = null;
                    try {
                        ts = pollTasks();
                        // 批量执行任务
                        execTasks(ts);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 从任务队列中获取批量任务
     */
    private List<Task> pollTasks() throws InterruptedException {
        List<Task> ts = new LinkedList<>();
        // 阻塞式获取一条任务
        Task t = bq.take();
        while (t != null) {
            ts.add(t);
            // 非阻塞式获取一条任务
            t = bq.poll();
        }
        return ts;
    }

    /**
     * 批量执行任务
     */
    private void execTasks(List<Task> ts) {

    }

}
