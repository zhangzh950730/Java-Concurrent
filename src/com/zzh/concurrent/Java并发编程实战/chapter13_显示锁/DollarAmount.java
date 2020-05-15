package com.zzh.concurrent.Java并发编程实战.chapter13_显示锁;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 10:02
 */
public class DollarAmount implements Comparable<DollarAmount> {
    private Double amount;

    public DollarAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(DollarAmount dollarAmount) {
        return this.amount.compareTo(dollarAmount.amount);
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void debit(DollarAmount dollarAmount) {
        this.amount = this.amount - dollarAmount.amount;
    }

    public void credit(DollarAmount dollarAmount) {
        this.amount = this.amount + dollarAmount.amount;
    }
}
