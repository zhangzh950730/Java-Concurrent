package com.zzh.concurrent.geektime.part1_并发理论基础.chapter1;

/**
 * 验证编译优化带来的有序性问题
 * <p>
 * instance = new Singleton();对应的CPU指令及执行顺序应为：
 * 1. 分配一块内存M；
 * 2. 将M的地址赋值给instance变量；
 * 3. 最后在内存M上初始化Singleton对象。
 * <p>
 * 但是实际编译器会优化指令顺序，按照132来执行。如果线程1执行完指令2之后发生线程切换，
 * 线程2判断到instance不为null，所以直接返回instance；
 * 但是instance此时还没有初始化,此时访问instance的成员变量会触发空指针
 *
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/14 15:59
 */
public class Singleton {
    private static Singleton instance;

    private static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(Singleton::getInstance);
        Thread thread2 = new Thread(Singleton::getInstance);
        Thread thread3 = new Thread(Singleton::getInstance);
        Thread thread4 = new Thread(Singleton::getInstance);
        Thread thread5 = new Thread(Singleton::getInstance);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("instance = " + instance);
    }
}
