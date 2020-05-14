package com.zzh.concurrent.Java并发编程实战.chapter2_线程安全性.servlet;

import com.zzh.concurrent.Java并发编程实战.NotThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 这个Servlet在没有足够原子性保证的情况下对其最近计算结果进行缓存(不要这么做)
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/12 17:44
 */
@NotThreadSafe
public class UnSafeCachingFactorizer extends HttpServlet {
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
