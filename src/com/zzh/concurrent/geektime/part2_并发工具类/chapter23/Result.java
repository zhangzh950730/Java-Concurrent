package com.zzh.concurrent.geektime.part2_并发工具类.chapter23;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/16 15:23
 */
public class Result {
    private int A;
    private int B;

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    @Override
    public String toString() {
        return "Result{" +
                "A=" + A +
                ", B=" + B +
                '}';
    }
}
