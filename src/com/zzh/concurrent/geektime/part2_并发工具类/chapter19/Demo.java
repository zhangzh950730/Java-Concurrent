package com.zzh.concurrent.geektime.part2_并发工具类.chapter19;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/15 14:59
 */
public class Demo {

    //
    //// 订单队列
    //Vector<P> pos;
    //// 派送单队列
    //Vector<D> dos;
    //// 执行回调的线程池
    //Executor executor =
    //        Executors.newFixedThreadPool(1);
    //final CyclicBarrier barrier =
    //        new CyclicBarrier(2, ()->{
    //            executor.execute(()->check());
    //        });
    //
    //void check(){
    //    P p = pos.remove(0);
    //    D d = dos.remove(0);
    //    // 执行对账操作
    //    diff = check(p, d);
    //    // 差异写入差异库
    //    save(diff);
    //}
    //
    //void checkAll(){
    //    // 循环查询订单库
    //    Thread T1 = new Thread(()->{
    //        while(存在未对账订单){
    //            // 查询订单库
    //            pos.add(getPOrders());
    //            // 等待
    //            barrier.await();
    //        }
    //    });
    //    T1.start();
    //    // 循环查询运单库
    //    Thread T2 = new Thread(()->{
    //        while(存在未对账订单){
    //            // 查询运单库
    //            dos.add(getDOrders());
    //            // 等待
    //            barrier.await();
    //        }
    //    });
    //    T2.start();
    //}
}
