package com.zzh.concurrent.geektime.part3_并发设计模式.chapter33;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/18 9:15
 */
public class Echo {
    public void echo() {
        try (final ServerSocketChannel ssc =ServerSocketChannel.open().bind(new InetSocketAddress(8080))){
            while (true) {
                // 接受请求
                SocketChannel sc = ssc.accept();
                // 每个请求都创建一个线程
                new Thread(() -> {
                    try {
                        // 读Socket
                        ByteBuffer rb = ByteBuffer.allocateDirect(1024);
                        sc.read(rb);
                        // 模拟处理请求
                        Thread.sleep(2000);
                        // 写Socket
                        ByteBuffer wb = (ByteBuffer) rb.flip();
                        sc.write(wb);
                        //关闭Socket
                        sc.close();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
