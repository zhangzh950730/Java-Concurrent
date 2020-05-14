package com.zzh.concurrent.Java并发编程实战.chapter7_取消与关闭;

import java.io.PrintWriter;
import java.util.concurrent.*;

/**
 * 使用ExecutorService的日志服务
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/14 10:27
 */
public class LogService1 {
    private static final long TIMEOUT = 1000;
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    private final ExecutorService exec = Executors.newSingleThreadExecutor();
    private final PrintWriter writer;

    public LogService1(PrintWriter writer) {
        this.writer = writer;
    }

    public void start() {

    }

    public void stop() throws InterruptedException {
        try {
            exec.shutdown();
            exec.awaitTermination(TIMEOUT, UNIT);
        } finally {
            writer.close();
        }
    }

    public void log(String msg) {
        try {
            exec.execute(new WriteTask(msg));
        } catch (RejectedExecutionException ignored) {

        }
    }

    private class WriteTask implements Runnable {
        private final String msg;

        public WriteTask(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            writer.println(msg);
        }
    }
}
