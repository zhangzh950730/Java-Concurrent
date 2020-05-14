package com.zzh.concurrent.Java并发编程实战.chapter3_对象的共享;


/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/12 19:40
 */
public class ThisEscape {

    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener(){
            @Override
            public void onEvent(Event e) {
                doSomething(e);
            }
        });
    }

    private void doSomething(Event e) {
        System.out.println("ThisEscape.doSomething");
    }
}
