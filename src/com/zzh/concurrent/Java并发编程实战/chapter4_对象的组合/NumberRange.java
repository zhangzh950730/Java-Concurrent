package com.zzh.concurrent.Java并发编程实战.chapter4_对象的组合;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/13 10:19
 */
public class NumberRange {
    // 不变性条件 lower<upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        // 不安全的"先检查后执行"
        if (i > upper.get()) {
            throw new IllegalArgumentException("can not set lower to " + i + " > upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        // 不安全的"先检查后执行"
        if (i < lower.get()) {
            throw new IllegalArgumentException("can not set lower to " + i + " < lower");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return i >= lower.get() && i <= upper.get();
    }
}
