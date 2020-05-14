package com.zzh.concurrent.Java并发编程实战.chapter8_线程池的使用;

import java.util.Set;

/**
 * 表示"搬箱子"之类谜题的接口
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/14 15:37
 */
public interface Puzzle<P, M> {

    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);
}
