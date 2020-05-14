package com.zzh.concurrent.Java并发编程实战.chapter7_取消与关闭;

import com.zzh.concurrent.Java并发编程实战.GuardedBy;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 可靠的取消操作
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/14 10:27
 */
public class LogService {
    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;

    @GuardedBy("this")
    private boolean isShutdown;
    @GuardedBy("this")
    private int reservations;

    public LogService(PrintWriter writer) {
        this.writer = writer;
        this.queue = new LinkedBlockingQueue<>(10);
        this.loggerThread = new LoggerThread();
    }

    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
            loggerThread.interrupt();
        }
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalStateException();
            }
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized ((LogService.this)) {
                        if (isShutdown && reservations == 0) {
                            break;
                        }
                    }
                    String msg = queue.take();
                    synchronized (LogService.this) {
                        --reservations;
                    }
                    writer.println(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    writer.close();
                }
            }
        }
    }
}
