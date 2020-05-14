package com.zzh.concurrent.Java并发编程实战.chapter3_对象的共享;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/12 19:36
 */
public class UnSafeStates {
    private String[] states = new String[]{"AK", "AL"};

    public String[] getStates() {
        return states;
    }
}
