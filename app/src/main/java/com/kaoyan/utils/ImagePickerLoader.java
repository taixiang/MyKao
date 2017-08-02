package com.kaoyan.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

/**
 * Created by tx on 2017/8/1.
 */

public class ImagePickerLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        LogUtil.i(" ImagePickerLoader  displayImage ");
        ImgManager.loadImage(activity,new File(path),imageView);

    }

    @Override
    public void clearMemoryCache() {
        LogUtil.i(" ImagePickerLoader  clearMemoryCache ");
    }
}
