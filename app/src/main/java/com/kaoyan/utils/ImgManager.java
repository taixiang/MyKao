package com.kaoyan.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kaoyan.R;


import java.io.File;

/**
 * Created by tx on 2017/7/18.
 * 图片加载工具，使用Glide类库
 */
public class ImgManager {
    //TODO 换默认图片 ic_launcher
    public static void loadImage(Context context, String url, int erroImg, int emptyImg, ImageView iv) {
        //原生 API
        Glide.with(context).load(url).skipMemoryCache(true).placeholder(emptyImg).error(erroImg).into(iv);
    }

    public static void loadImage(Context context, String url, ImageView iv) {

        Glide.with(context).load(url).dontAnimate().error(R.mipmap.ic_launcher).into(iv);
    }

    public static void loadImageWithNoAnim(Context context, String url, ImageView iv){
        Glide.with(context).load(url).dontAnimate().error(R.mipmap.ic_launcher).into(iv);

    }

    public static void loadGifImage(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.mipmap.ic_launcher).into(iv);
    }


    public static void loadCircleImage(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).error(R.mipmap.ic_launcher).transform(new GlideCircleTransform(context)).into(iv);
    }

    public static void loadRoundCornerImage(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).error(R.mipmap.ic_launcher).transform(new GlideRoundTransform(context, 10)).into(iv);
    }


    public static void loadImage(Context context, final File file, final ImageView imageView) {
        Glide.with(context).load(file).into(imageView);
    }

    public static void loadImage(Context context, final int resourceId, final ImageView imageView) {
        Glide.with(context).load(resourceId).into(imageView);
    }


}
