package com.kaoyan.base;

import android.app.Application;
import android.content.Context;

import com.kaoyan.api.RetrofitService;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.utils.ToastUtils;

/**
 * Created by tx on 2017/7/17.
 */
public class BaseApplication extends Application {
    private static BaseApplication app ;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //release包需注释
        LogUtil.isDebug = true;
        RetrofitService.init(this);
    }

    public static BaseApplication getInstance() {
        return app;
    }


}