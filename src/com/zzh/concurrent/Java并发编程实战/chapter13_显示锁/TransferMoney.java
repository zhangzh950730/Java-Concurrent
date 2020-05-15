package com.zzh.concurrent.Java并发编程实战.chapter13_显示锁;


import java.util.concurrent.TimeUnit;

/**
 * 通过tryLock来避免锁顺序死锁
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 10:00
 */
public class TransferMoney {

    public boolean transferMoney(Account from, Account to, DollarAmount amount, long timeout, TimeUnit unit) {
        long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
        long randMod = getRandomDelayModulusNanos(timeout, unit);
        long stopTime = System.nanoTime() + unit.toNanos(timeout);

        while (true) {
            if (from.lock.tryLock()) {
                try {
                    if (to.lock.tryLock()) {
                        try {
                            if (from.getBalance().compareTo(amount) < 0) {
                                throw new InsufficientFundsException();
                            } else {
                                from.debit(amount);
                                to.credit(amount);
                                return true;
                            }
                        } finally {
                            to.lock.unlock();
                        }
                    }
                } finally {
                    from.lock.unlock();
                }
            }
            if (System.nanoTime() < stopTime) {
                return false;
            }
            //NANOSECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
        }
    }


    private long getRandomDelayModulusNanos(long timeout, TimeUnit unit) {
        // TODO: 2020/5/15
        return 0;
    }

    private long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
        // TODO: 2020/5/15
        return 0;
    }
}
