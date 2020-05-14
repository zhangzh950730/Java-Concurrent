package com.zzh.concurrent.Java并发编程实战.chapter7_取消与关闭;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 在ExecutorService中跟踪正在关闭之后被取消的任务
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/14 11:26
 */
public class TrackingExecutor extends AbstractExecutorService {
    private final ExecutorService exec;
    private final Set<Runnable> tasksCancelledAtShutdown;

    public TrackingExecutor(ExecutorService exec) {
        this.exec = exec;
        tasksCancelledAtShutdown = new HashSet<>();
    }

    public List<Runnable> getCancelledTasks() {
        if (!exec.isTerminated()) {
            throw new IllegalStateException();
        }
        return new ArrayList<>(tasksCancelledAtShutdown);
    }

    @Override
    public void shutdown() {
        exec.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return exec.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return exec.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout, unit);
    }

    @Override
    public void execute(Runnable runnable) {
        exec.execute(() -> {
            try {
                runnable.run();
            } finally {
                if (TrackingExecutor.this.isShutdown() && Thread.currentThread().isInterrupted()) {
                    tasksCancelledAtShutdown.add(runnable);
                }
            }
        });
    }
}
