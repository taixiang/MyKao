package com.kaoyan.testhttp;

import android.content.Context;

import com.kaoyan.api.CommonApi;
import com.kaoyan.api.IApi;
import com.kaoyan.bean.BaseBean;
import com.kaoyan.model.BaseItem;
import com.kaoyan.model.NovelItem;
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
import retrofit2.converter.gson.GsonConverterFactory;
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


    public static <T> void getData(Observable<BaseItem<T>> ob, LifecycleTransformer<String> l, final DataView dataView, final String requestTag) {
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseItem<T>, Observable<String>>() {
                    @Override
                    public Observable<String> call(BaseItem<T> tBaseItem) {
                        LogUtil.i(" pros utils ==   " + tBaseItem.pros.toString());
                        return Observable.just(tBaseItem.pros.toString());
                    }
                }).doOnSubscribe(new Action0() {
            @Override
            public void call() {
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


    public static <T> void toSub(Observable<BaseItem<T>> ob, Subscriber<T> subscriber, LifecycleTransformer<T> l) {
        ob.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseItem<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseItem<T> tBaseItem) {
                        return Observable.just(tBaseItem.pros);
                    }
                }).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                LogUtil.i("  threadname doOnSubscribe ==" + Thread.currentThread().getName());
            }
        }).compose(l).subscribe(subscriber);
    }

//    public static <T> void getData2(Observable<BaseBean<T>> ob, LifecycleTransformer<String> l, final DataView dataView, final String requestTag) {
//        ob.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Func1<BaseBean<T>, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(BaseBean<T> tBaseItem) {
//                        LogUtil.i(" pros utils ==   " + tBaseItem.getPros().toString());
//                        return Observable.just(GsonUtils.toJson(tBaseItem.getPros()));
//                    }
//                }).doOnSubscribe(new Action0() {
//            @Override
//            public void call() {
//            }
//        }).compose(l).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                dataView.onGetDataFailured(e, requestTag);
//            }
//
//            @Override
//            public void onNext(String t) {
//                LogUtil.i(" t utils == " + t);
//                dataView.onGetDataSuccess(t, requestTag);
//            }
//        });
//    }



    public static <T> void getData3(Observable<String> ob, LifecycleTransformer<String> l, final DataView dataView, final String requestTag) {
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String tBaseItem) {
                        LogUtil.i(" pros utils ==   " + tBaseItem);
                        BaseBean baseBean = GsonUtils.jsonToClass(tBaseItem,BaseBean.class);
                        return Observable.just(tBaseItem);
                    }
                }).doOnSubscribe(new Action0() {
            @Override
            public void call() {
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


//    public static <T> void toSub2(Observable<BaseBean<T>> ob, LifecycleTransformer<BaseBean> l,final DataView dataView, final String requestTag) {
//        ob.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Func1<BaseBean<T>, Observable<BaseBean>>() {
//                    @Override
//                    public Observable<BaseBean> call(BaseBean tBaseItem) {
//                        LogUtil.i("  basebean1111 ==" + tBaseItem.toString());
//                        return Observable.just(tBaseItem);
//                    }
//                }).doOnSubscribe(new Action0() {
//            @Override
//            public void call() {
//                LogUtil.i("  threadname doOnSubscribe ==" + Thread.currentThread().getName());
//            }
//        }).compose(l).subscribe(new Subscriber<BaseBean>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(BaseBean t) {
//                LogUtil.i("  basebean22222 ==" + t.toString());
//            }
//        });
//    }
}