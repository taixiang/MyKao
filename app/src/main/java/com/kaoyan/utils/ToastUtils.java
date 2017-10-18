package com.kaoyan.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.kaoyan.base.BaseApplication;

/**
 * Created by tx on 2017/7/17.
 * 避免同样的信息多次触发重复弹出的问题
 */
public class ToastUtils {

    private static String oldMsg;
    private static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;


    public static void showToast(Context context,String s) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance(), s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
            oneTime = twoTime;
        }
    }

    public static void showToast(Context context,int resId) {
        showToast(context,context.getString(resId));
    }
}
