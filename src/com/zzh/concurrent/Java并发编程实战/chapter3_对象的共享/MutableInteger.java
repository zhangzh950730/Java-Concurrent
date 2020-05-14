package com.zzh.concurrent.Java并发编程实战.chapter3_对象的共享;

import com.zzh.concurrent.Java并发编程实战.NotThreadSafe;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/12 19:24
 */
@NotThreadSafe
public class MutableInteger {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
