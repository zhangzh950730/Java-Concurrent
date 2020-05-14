package com.zzh.concurrent.Java并发编程实战.chapter6_任务执行;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/13 18:23
 */
public class TaskExecutionWebServer {
    private static final int N_THREADS = 100;
    private static final Executor EXEC = Executors.newFixedThreadPool(N_THREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = () -> handleRequest(connection);
            EXEC.execute(task);
        }
    }

    private static void handleRequest(Socket connection) {
        // TODO: 2020/5/13
    }
}
