package com.zzh.concurrent.Java并发编程实战.chapter2_线程安全性.servlet;

import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 无状态的Servlet
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/12 17:35
 */
@ThreadSafe
public class StatelessFactorizer extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(resp, factors);
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
