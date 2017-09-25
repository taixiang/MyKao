package com.kaoyan.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.github.moduth.blockcanary.BlockCanary;
import com.kaoyan.R;
import com.kaoyan.api.RetrofitService;
import com.kaoyan.database.DaoMaster;
import com.kaoyan.database.DaoSession;
import com.kaoyan.servicedemo.RequestUtil;
import com.kaoyan.utils.AppBlockCanaryContext;
import com.kaoyan.utils.GreenDaoManager;
import com.kaoyan.utils.ImagePickerLoader;
import com.kaoyan.utils.LogUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.greendao.database.Database;

/**
 * Created by tx on 2017/7/17.
 */
public class BaseApplication extends Application {
    private static BaseApplication app ;
    private static String DB_NAME = "yantuvip_db";
    private static DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
//        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
            BlockCanary.install(this,new AppBlockCanaryContext()).start();
            LogUtil.isDebug = true;
//        }
        RetrofitService.init(this);
        RequestUtil.init(this);
        GreenDaoManager.getInstance(this);
        initSmartRefreshLayout();
//        CrashHandler.getInstance().init(this);
        initPicker();
//        _initDatabase();
    }

    /**
     * 初始化数据库
     */
    private static void _initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getInstance(), DB_NAME);
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
    }

    private static void  initPicker(){
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setMultiMode(false);
        imagePicker.setImageLoader(new ImagePickerLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(true);
        imagePicker.setSaveRectangle(true);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        imagePicker.setFocusWidth(800);
        imagePicker.setFocusHeight(800);
        imagePicker.setOutPutX(1000);
        imagePicker.setOutPutY(1000);
    }

    private static void initSmartRefreshLayout(){
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
                @NonNull
                @Override
                public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                    layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                    ClassicsHeader header = new ClassicsHeader(context);
                    return header;//指定为经典Header，默认是 贝塞尔雷达Header
                }
            });
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
                @NonNull
                @Override
                public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                    //指定为经典Footer，默认是 BallPulseFooter
                    return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
                }
            });
    }

    public static BaseApplication getInstance() {
        return app;
    }


}