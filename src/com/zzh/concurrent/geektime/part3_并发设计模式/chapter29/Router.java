package com.zzh.concurrent.geektime.part3_并发设计模式.chapter29;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/2/17 15:41
 */
public final class Router {
    private final String ip;
    private final Integer port;
     final String iface;

    public Router(String ip, Integer port, String iface) {
        this.ip = ip;
        this.port = port;
        this.iface = iface;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Router) {
            Router r = (Router) obj;
            return (iface.equals(r.iface)
                    && ip.equals(r.ip)
                    && port.equals(r.port));
        }
        return false;
    }
}
