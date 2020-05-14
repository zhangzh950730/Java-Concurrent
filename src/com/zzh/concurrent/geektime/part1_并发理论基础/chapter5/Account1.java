package com.zzh.concurrent.geektime.part1_并发理论基础.chapter5;

/**
 * 演示可能产生死锁的例子
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/14 17:36
 */
public class Account1 {

    private int balance;

    // 转账
    void transfer(Account1 target, int amt) {
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
    }
}
