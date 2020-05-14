package com.zzh.concurrent.Java并发编程实战.chapter7_取消与关闭;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/14 10:08
 */
public interface CancellableTask<T> extends Callable<T> {

    void cancel();

    RunnableFuture<T> newTask();
}
