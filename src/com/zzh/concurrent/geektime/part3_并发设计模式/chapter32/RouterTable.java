package com.zzh.concurrent.geektime.part3_并发设计模式.chapter32;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/17 15:44
 */
public class RouterTable {
    private ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> rt = new ConcurrentHashMap<>();
    volatile boolean changed;

    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    public void startLocalSaver() {
        ses.scheduleWithFixedDelay(this::autoSave, 1, 1, TimeUnit.MINUTES);
    }

    private void autoSave() {
        if (!changed) {
            return;
        }
        changed = false;
        this.save2Local();
    }

    private void save2Local() {
    }

    public Set<Router> get(String iface) {
        return rt.get(iface);
    }

    public void remove(Router router) {
        Set<Router> set = rt.get(router.iface);
        if (set != null) {
            set.remove(router);
        }
    }

    public void add(Router router) {
        Set<Router> set = rt.computeIfAbsent(router.iface,
                r -> new CopyOnWriteArraySet<>());
        set.add(router);
    }
}
