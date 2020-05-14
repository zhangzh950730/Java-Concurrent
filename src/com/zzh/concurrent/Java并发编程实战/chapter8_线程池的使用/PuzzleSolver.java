package com.zzh.concurrent.Java并发编程实战.chapter8_线程池的使用;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/14 16:20
 */
public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {
    private final AtomicInteger taskCount = new AtomicInteger(0);

    public PuzzleSolver(Puzzle<P, M> puzzle) {
        super(puzzle);
    }

    @Override
    protected Runnable newTask(P p, M m, Node<P, M> n) {
        return new CountingSolverTask(p, m, n);
    }

    class CountingSolverTask extends SolverTask {

        public CountingSolverTask(P pos, M move, Node<P, M> prev) {
            super(pos, move, prev);
            taskCount.incrementAndGet();
        }

        @Override
        public void run() {
            try {
                super.run();
            } finally {
                if (taskCount.decrementAndGet() == 0) {
                    solution.setValue(null);
                }
            }
        }
    }
}
