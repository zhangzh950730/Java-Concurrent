package com.zzh.concurrent.Java并发编程实战.chapter3_对象的共享;


import com.zzh.concurrent.Java并发编程实战.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/12 21:47
 */
@Immutable
public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
