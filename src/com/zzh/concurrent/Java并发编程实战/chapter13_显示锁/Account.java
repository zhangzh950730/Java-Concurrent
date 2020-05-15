package com.zzh.concurrent.Java并发编程实战.chapter13_显示锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 10:01
 */
public class Account {
    public Lock lock = new ReentrantLock();
    private DollarAmount DollarAmount;

    public DollarAmount getBalance() {
        return DollarAmount;
    }

    public void debit(DollarAmount amount) {
        DollarAmount.debit(amount);
    }

    public void credit(DollarAmount amount) {
        DollarAmount.credit(amount);
    }
}
