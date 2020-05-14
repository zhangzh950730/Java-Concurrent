package com.zzh.concurrent.Java并发编程实战.chapter2_线程安全性.servlet;

import com.zzh.concurrent.Java并发编程实战.GuardedBy;
import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/12 17:53
 */
@ThreadSafe
public class SynchronizedFactorizer extends HttpServlet {
    @GuardedBy("this")
    private AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    @GuardedBy("this")
    private AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    /**
     * 这个Servlet能正确地缓存最新的计算结果,但并发性却非常糟糕(不要这么做)
     * 原因: synchronized修饰实例方法,同一时刻只能有一个线程访问该方法
     */
    @Override
    protected synchronized void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber.get())) {
            encodeIntoResponse(resp, lastFactors.get());
        } else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(resp, factors);
        }
    }

    private BigInteger extractFromRequest(HttpServletRequest req) {
        // TODO: 2020/5/12
        return null;
    }

    private BigInteger[] factor(BigInteger i) {
        // TODO: 2020/5/12
        return new BigInteger[0];
    }

    private void encodeIntoResponse(HttpServletResponse resp, BigInteger[] factors) {
        // TODO: 2020/5/12
    }
}
