package com.zzh.concurrent.Java并发编程实战.chapter6_任务执行;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/13 21:04
 */
public class FutureRender {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = () ->
                imageInfos.stream().map(ImageInfo::downloadImage).collect(Collectors.toList());
        Future<List<ImageData>> future = executor.submit(task);

        renderText(source);

        try {
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData) {
                renderImage(data);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            future.cancel(true);
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }

    private void renderImage(ImageData imageData) {
        // TODO: 2020/5/13
    }

    private void renderText(CharSequence source) {
        // TODO: 2020/5/13
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        // TODO: 2020/5/13
        return null;
    }

    private RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }
}
