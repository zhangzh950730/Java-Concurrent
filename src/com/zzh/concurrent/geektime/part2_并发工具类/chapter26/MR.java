package com.zzh.concurrent.geektime.part2_并发工具类.chapter26;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/17 10:00
 */
public class MR extends RecursiveTask<Map<String, Long>> {
    private String[] fc;
    private int start, end;

    public MR(String[] fc, int fr, int to) {
        this.fc = fc;
        this.start = fr;
        this.end = to;
    }

    @Override
    protected Map<String, Long> compute() {
        if (end - start == 1) {
            return calc(fc[start]);
        } else {
            int mid = (start + end) / 2;
            MR mr1 = new MR(fc, start, mid);
            mr1.fork();
            MR mr2 = new MR(fc, mid, end);
            return merge(mr2.compute(), mr1.join());
        }
    }

    private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2) {
        Map<String, Long> result = new HashMap<>(r1);
        r2.forEach((key, value) -> {
            result.merge(key, value, Long::sum);
        });
        return result;
    }

    private Map<String, Long> calc(String line) {
        Map<String, Long> result = new HashMap<>();
        String[] words = line.split("\\s+");

        for (String word : words) {
            Long value = result.get(word);
            if (value != null) {
                result.put(word, value + 1);
            } else {
                result.put(word, 1L);
            }
        }
        return result;
    }
}
