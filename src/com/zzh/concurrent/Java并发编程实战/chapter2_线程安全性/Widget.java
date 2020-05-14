package com.zzh.concurrent.Java并发编程实战.chapter2_线程安全性;

/**
 * 可重入锁示例,子类中synchronized方法会调用父类synchronized方法,如果锁不可冲入,那么将导致线程永远停顿
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/12 18:05
 */
public class Widget {
    public synchronized void doSomething() {
        System.out.println("Widget.doSomething");
        // TODO: 2020/5/12
    }
}

class LoggingWidget extends Widget{
    @Override
    public synchronized void doSomething() {
        System.out.println(toString()+" : calling doSomething");
        super.doSomething();
    }
}
