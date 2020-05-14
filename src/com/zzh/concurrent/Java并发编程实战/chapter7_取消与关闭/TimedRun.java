package com.zzh.concurrent.Java并发编程实战.chapter7_取消与关闭;

import java.util.concurrent.*;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/14 9:42
 */
public class TimedRun {
    //private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(10);
    private static final ScheduledExecutorService taskExec = Executors.newScheduledThreadPool(10);

    /*
    public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        cancelExec.schedule(taskThread::interrupt, timeout, unit);
        r.run();
    }*/


 /*   public static void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if (t != null) {
                    throw launderThrowable(t);
                }
            }

            private RuntimeException launderThrowable(Throwable t) {
                if (t instanceof RuntimeException) {
                    return (RuntimeException) t;
                } else if (t instanceof Error) {
                    throw (Error) t;
                } else {
                    throw new IllegalStateException("Not unchecked", t);
                }
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();

        cancelExec.schedule(taskThread::interrupt, timeout, unit);

        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }*/

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        } finally {
            task.cancel(true);
        }
    }

    private static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }
}
