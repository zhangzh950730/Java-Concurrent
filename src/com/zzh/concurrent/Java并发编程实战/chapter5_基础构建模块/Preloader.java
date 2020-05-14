package com.zzh.concurrent.Java并发编程实战.chapter5_基础构建模块;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/13 15:47
 */
public class Preloader {
    private final FutureTask<ProductInfo> future = new FutureTask<>(this::loadProductInfo);

    private ProductInfo loadProductInfo() {
        // TODO: 2020/5/13
        return null;
    }

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataloadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataloadException) {
                throw (DataloadException) cause;
            } else {
                throw launderThrowable(cause);
            }
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
