package com.zzh.concurrent.geektime.part1_并发理论基础.chapter5;

/**
 * 解决死锁方案3：破坏循环等待条件
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/14 17:51
 */
public class Account3 {
    private int id;
    private int balance;

    // 转账
    void transfer(Account3 target, int amt) {
        Account3 left = this;
        Account3 right = target;
        if (this.id > target.id) {
            left = target;
            right = this;
        }
        // 锁定序号小的账户
        synchronized (left) {
            // 锁定序号大的账户
            synchronized (right) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}
