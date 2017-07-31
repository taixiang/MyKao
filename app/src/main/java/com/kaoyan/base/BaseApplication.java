package com.kaoyan.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kaoyan.BuildConfig;
import com.kaoyan.api.RetrofitService;
import com.kaoyan.database.DaoMaster;
import com.kaoyan.database.DaoSession;
import com.kaoyan.utils.CrashHandler;
import com.kaoyan.utils.GreenDaoManager;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.utils.ToastUtils;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.greendao.database.Database;

/**
 * Created by tx on 2017/7/17.
 */
public class BaseApplication extends Application {
    private static BaseApplication app ;
    private static String DB_NAME = "yantuvip_db";
    private DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
            LogUtil.isDebug = true;
        }
        RetrofitService.init(this);
        GreenDaoManager.getInstance(this);
        CrashHandler.getInstance().init(this);
//        _initDatabase();
    }

    /**
     * 初始化数据库
     */
    private void _initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getInstance(), DB_NAME);
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
    }

    public static BaseApplication getInstance() {
        return app;
    }


}