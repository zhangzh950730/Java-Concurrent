package com.zzh.concurrent.Java并发编程实战.chapter8_线程池的使用;

import com.zzh.concurrent.Java并发编程实战.Immutable;

import java.util.LinkedList;
import java.util.List;

/**
 * 用于谜题解决框架的链表节点
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/14 15:38
 */
@Immutable
public class Node<P, M> {
    final P pos;
    final M move;
    final Node<P, M> prev;

    public Node(P pos, M move, Node<P, M> prev) {
        this.pos = pos;
        this.move = move;
        this.prev = prev;
    }

    List<M> asMoveList() {
        List<M> solution = new LinkedList<>();
        for (Node<P, M> n = this; n.move != null; n = n.prev) {
            solution.add(0, n.move);
        }
        return solution;
    }

}
