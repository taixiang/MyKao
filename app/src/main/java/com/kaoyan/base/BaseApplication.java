package com.kaoyan.base;

import android.app.Application;
import android.content.Context;

import com.kaoyan.BuildConfig;
import com.kaoyan.api.RetrofitService;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.utils.ToastUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by tx on 2017/7/17.
 */
public class BaseApplication extends Application {
    private static BaseApplication app ;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
            LogUtil.isDebug = true;
        }
        RetrofitService.init(this);

    }

    public static BaseApplication getInstance() {
        return app;
    }


}