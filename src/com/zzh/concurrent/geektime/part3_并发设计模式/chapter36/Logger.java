package com.zzh.concurrent.geektime.part3_并发设计模式.chapter36;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/18 15:01
 */
public class Logger {
    // 任务队列
    final BlockingQueue<LogMsg> bq = new ArrayBlockingQueue<>(1000);
    // flush批量
    static final int batchSize = 500;
    // 只需要一个线程写日志
    ExecutorService es = Executors.newFixedThreadPool(1);

    /**
     * 启动写日志线程
     */
    void start() {
        try {
            File file = File.createTempFile("foo", ".log");
            final FileWriter writer = new FileWriter(file);
            es.execute(() -> {
                // 未刷盘日志数量
                int curIdx = 0;
                long preFT = System.currentTimeMillis();
                while (true) {
                    try {
                        LogMsg log = bq.poll(5, TimeUnit.SECONDS);
                        // 写日志
                        if (log != null) {
                            writer.write(log.toString());
                            ++curIdx;
                        }
                        // 如果不存在未刷盘数据，则无需刷盘
                        if (curIdx <= 0) {
                            continue;
                        }
                        // 根据规则刷盘
                        if (log != null && log.level == LEVEL.ERROR
                                || curIdx == batchSize
                                || System.currentTimeMillis() - preFT > 5000) {
                            writer.flush();
                            curIdx = 0;
                            preFT = System.currentTimeMillis();
                        }
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            writer.flush();
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //写INFO级别日志
    void info(String msg) {
        try {
            bq.put(new LogMsg(LEVEL.INFO, msg));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //写ERROR级别日志
    void error(String msg) {
        try {
            bq.put(new LogMsg(LEVEL.ERROR, msg));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //日志级别
    enum LEVEL {INFO, ERROR}

    static class LogMsg {
        LEVEL level;
        String msg;

        public LogMsg(LEVEL level, String msg) {
            this.level = level;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "LogMsg{" +
                    "level=" + level +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}
