package com.zzh.concurrent.geektime.part1_并发理论基础.chapter5;

import java.util.ArrayList;
import java.util.List;

/**
 * 解决死锁方案1：破坏占用且等待条件
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/14 17:40
 */
public class Account2 {
    private Allocator actr;
    private int balance;

    //转账
    void transfer(Account2 target, int amt) {
        while (!actr.apply(this, target)) {

        }
        try {
            // 锁定转出账户
            synchronized (this) {
                // 锁定转入账户
                synchronized (target) {
                    if (this.balance > amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } finally {
            actr.free(this, target);
        }
    }

}

class Allocator {
    private List<Object> als = new ArrayList<>();

    // 一次性申请所有资源
    synchronized boolean apply(Object from, Object to) {
        if (als.contains(from) || als.contains(to)) {
            return false;
        } else {
            als.add(from);
            als.add(to);
        }
        return true;
    }

    // 归还资源
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
    }
}
