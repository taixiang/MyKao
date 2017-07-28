package com.kaoyan.utils;

import android.content.Context;

/**
 * Created by tx on 2017/7/28.
 * 像素转换工具
 */

public class DensityUtil {

    /**
     * px转dp
     * @param context
     * @param px
     * @return
     */
    public static int pxToDp(Context context, int px){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(px / scale + 0.5f);
    }

    /**
     * dp转px
     * @param context
     * @param dp
     * @return
     */
    public static int dpToPx(Context context, int dp){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
