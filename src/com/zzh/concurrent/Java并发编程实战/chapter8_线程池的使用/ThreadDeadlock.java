package com.zzh.concurrent.Java并发编程实战.chapter8_线程池的使用;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 单线程Executor中发生死锁
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/14 14:15
 */
public class ThreadDeadlock {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class RenderPageTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // 将发生死锁 由于任务在等待子任务的结果
            return header.get() + page + footer.get();
        }

        private class LoadFileTask implements Callable<String> {
            private LoadFileTask(String file) {
                // TODO: 2020/5/14
            }

            @Override
            public String call() throws Exception {
                // TODO: 2020/5/14
                return null;
            }
        }

        private String renderBody() {
            // TODO: 2020/5/14
            return null;
        }
    }

}
