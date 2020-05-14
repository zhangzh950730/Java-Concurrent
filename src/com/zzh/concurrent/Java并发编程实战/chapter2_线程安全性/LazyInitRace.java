package com.zzh.concurrent.Java并发编程实战.chapter2_线程安全性;

import com.zzh.concurrent.Java并发编程实战.NotThreadSafe;

/**
 * 不安全的 懒加载-单例模式
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/12 17:26
 */
@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }

    class ExpensiveObject {
        private ExpensiveObject() {

        }
    }
}


