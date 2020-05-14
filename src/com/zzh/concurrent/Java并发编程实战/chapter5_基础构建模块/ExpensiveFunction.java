package com.zzh.concurrent.Java并发编程实战.chapter5_基础构建模块;

import java.math.BigInteger;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/13 16:31
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        // 在经过很长时间的计算后
        return new BigInteger(arg);
    }
}
