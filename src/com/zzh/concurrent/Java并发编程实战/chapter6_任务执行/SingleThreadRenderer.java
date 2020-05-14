package com.zzh.concurrent.Java并发编程实战.chapter6_任务执行;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="zhangzh950730@gmail.com" >ZhangZhiHao</a>
 * @since 2020/5/13 20:54
 */
public class SingleThreadRenderer {
    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();
        for (ImageInfo imageInfo : scanForImageInfo(source)) {
            imageData.add(imageInfo.downloadImage());
        }
        for (ImageData data : imageData) {
            renderImage(data);
        }
    }

    private void renderText(CharSequence source) {
        // TODO: 2020/5/13
    }

    private void renderImage(ImageData data) {
        // TODO: 2020/5/13
    }

    private ImageInfo[] scanForImageInfo(CharSequence source) {
        // TODO: 2020/5/13
        return new ImageInfo[0];
    }
}




