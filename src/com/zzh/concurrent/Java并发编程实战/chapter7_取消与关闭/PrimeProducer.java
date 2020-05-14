package com.zzh.concurrent.Java并发编程实战.chapter7_取消与关闭;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/14 9:00
 */
public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) {
                queue.put(p = p.nextProbablePrime());
                System.out.println(queue);
            }
        } catch (InterruptedException consumed) {
            // 允许线程退出
        }
    }

    public void cancel() {
        interrupt();
    }

}
