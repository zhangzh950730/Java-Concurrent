package com.zzh.concurrent.Java并发编程实战.chapter5_基础构建模块;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/13 16:30
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
