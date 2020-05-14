package com.zzh.concurrent.Java并发编程实战.chapter5_基础构建模块;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/13 15:05
 */
public class FileCrawlerIndex {
    public static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>();
        FileFilter filter = pathname -> true;
        for (File root : roots) {
            new Thread(new FileCrawler(queue, filter, root)).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}
