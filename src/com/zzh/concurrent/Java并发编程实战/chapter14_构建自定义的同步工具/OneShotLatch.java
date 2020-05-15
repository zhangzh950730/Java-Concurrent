package com.zzh.concurrent.Java并发编程实战.chapter14_构建自定义的同步工具;

import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 16:23
 */
@ThreadSafe
public class OneShotLatch {
    private final Sync sync = new Sync();

    public void signal() throws InterruptedException {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int ignored) {
            return getState() == 1 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int ignored) {
            setState(1);
            return true;
        }
    }
}


