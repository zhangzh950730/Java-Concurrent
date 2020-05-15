package com.zzh.concurrent.Java并发编程实战.chapter15_原子变量与非阻塞同步机制;

import com.zzh.concurrent.Java并发编程实战.Immutable;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 17:20
 */
public class CasNumberRange {
    @Immutable
    private static class IntPair {
        final int lower;
        final int upper;

        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    private final AtomicReference<IntPair> values = new AtomicReference<>(new IntPair(0, 0));

    public int getLower() {
        return values.get().lower;
    }

    public int getUpper() {
        return values.get().upper;
    }

    public void setLower(int i) {
        while (true) {
            IntPair oldv = values.get();
            if (i > oldv.upper) {
                throw new IllegalArgumentException("can not set lower to " + i + " >upper");
            }
            IntPair newv = new IntPair(i, oldv.upper);
            if (values.compareAndSet(oldv, newv)) {
                return;
            }
        }
    }

    public void setUpper(int i) {
        while (true) {
            IntPair oldv = values.get();
            if (i < oldv.lower) {
                throw new IllegalArgumentException("can not set upper to " + i + " <lower");
            }
            IntPair newv = new IntPair(oldv.lower, i);
            if (values.compareAndSet(oldv, newv)) {
                return;
            }
        }
    }

}
