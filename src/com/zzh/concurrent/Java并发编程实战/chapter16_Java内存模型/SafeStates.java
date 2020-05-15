package com.zzh.concurrent.Java并发编程实战.chapter16_Java内存模型;

import com.zzh.concurrent.Java并发编程实战.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/15 19:32
 */
@ThreadSafe
public class SafeStates {
    private final Map<String, String> states;

    public SafeStates() {
        states = new HashMap<>();
        states.put("alaskak", "AK");
        states.put("alabama", "AL");
        states.put("wyoming", "WY");
    }

    public String getAbbreviation(String s) {
        return states.get(s);
    }
}
