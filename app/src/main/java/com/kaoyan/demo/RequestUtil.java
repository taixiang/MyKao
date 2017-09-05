package com.kaoyan.demo;

import android.content.Context;

import com.kaoyan.api.CommonApi;
import com.kaoyan.api.IApi;
import com.kaoyan.bean.BaseBean;
import com.kaoyan.model.BaseItem;
import com.kaoyan.utils.GsonUtils;
import com.kaoyan.utils.LogUtil;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tx on 2017/9/2.
 */

public class RequestUtil {

    private static final String BASE_URL = "http://m.iisbn.com/";
    private static final String URL = "http://www.iisbn.com/";
    public static IApi msgApi;
    public static CommonApi commonApi;

    private RequestUtil() {
        throw new AssertionError();
    }

    public static void init(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(loggerInterceptor);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存策略
        Cache cache = new Cache(new File(context.getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
//                .addInterceptor(tokenInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        msgApi = retrofit.create(IApi.class);
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(URL)
                .build();
        commonApi = retrofit.create(CommonApi.class);
    }


    private static HttpLoggingInterceptor.Logger loggerInterceptor = new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            LogUtil.i(" 》》》》  ", " message == " + message + "");
        }
    };

    public static void getData3(Observable<String> ob, LifecycleTransformer<String> l, final DataView dataView, final String requestTag) {
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String tBaseItem) {
                        BaseBean baseBean = GsonUtils.jsonToClass(tBaseItem,BaseBean.class);
                        return Observable.just(tBaseItem);
                    }
                }).compose(l).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                dataView.onGetDataFailured(e, requestTag);
            }

            @Override
            public void onNext(String t) {
                LogUtil.i(" t utils == " + t);
                dataView.onGetDataSuccess(t, requestTag);
            }
        });
    }
}