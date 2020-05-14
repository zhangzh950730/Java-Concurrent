package com.zzh.concurrent.Java并发编程实战.chapter5_基础构建模块;

import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/13 16:50
 */
@ThreadSafe
public class Factorizer extends HttpServlet {
    private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<>(this::factor);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BigInteger i = extractFromRequest(req);
            encodeIntoResponse(resp, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(resp, "factorization interrupted");
        }
    }

    private void encodeError(HttpServletResponse resp, String factorization_interrupted) {
        // TODO: 2020/5/13
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
