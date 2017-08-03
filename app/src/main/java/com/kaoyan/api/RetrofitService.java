package com.kaoyan.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.kaoyan.base.BaseApplication;
import com.kaoyan.model.BaseItem;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.model.NovelItem;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.utils.NetUtil;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tx on 2017/7/17.
 * 网络获取类
 */

public class RetrofitService {
    //设缓存有效期为1天  暂未用缓存
    static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    private static final String BASE_URL = "http://m.iisbn.com/";
    private static final String URL = "http://www.iisbn.com/";
    public static IApi msgApi;
    public static CommonApi commonApi;

    private RetrofitService() {
        throw new AssertionError();
    }

    public static void init(Context context){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(loggerInterceptor);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存策略
        Cache cache = new Cache(new File(context.getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
//                .addInterceptor(interceptor)
//                .addInterceptor(tokenInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        msgApi = retrofit.create(IApi.class);
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(URL)
                .build();
        commonApi = retrofit.create(CommonApi.class);
    }

    private static HttpLoggingInterceptor.Logger loggerInterceptor = new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            LogUtil.i(" 》》》》  "," message == "+message+"");
        }
    };

    public static <T> void toSub(Observable<BaseItem<T>> ob,Subscriber<T> subscriber,LifecycleTransformer<T> l){
        ob.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new ResultFilter<T>()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                LogUtil.i("  threadname doOnSubscribe =="+Thread.currentThread().getName());
            }
        }).compose(l).subscribe(subscriber);

    }

    public static <T> void mergeSub(Subscriber<T> subscriber,LifecycleTransformer<T> l,Observable<BaseItem<T>>... tt){

        Observable.merge(tt).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new ResultFilter<T>()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
//                LogUtil.i("  threadname == "+Thread.currentThread().getName());

            }
        }).compose(l).subscribe(subscriber);
    }

    private static class ResultFilter<T> implements Func1<BaseItem<T>, T> {
        @Override
        public T call(BaseItem<T> tHttpBean) {
//            if(tHttpBean.result.equals("true")){
//                throw new ApiException(1);
//            }
//            LogUtil.i(" httpBean ==   "+tHttpBean);
//
//            LogUtil.i("  threadname ResultFilter== "+Thread.currentThread().getName());

            return tHttpBean.pros;
        }
    }

    /**
     * 带loading
     * @param ob
     * @param subscriber
     * @param l
     * @param action0
     * @param <T>
     */
    public static <T> void doSubscribeWithLoading(Observable<T> ob, Subscriber<T> subscriber, LifecycleTransformer<T> l, Action0 action0){
        ob.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(action0).compose(l).subscribe(subscriber);
    }

    /**
     * 打印返回的json数据拦截器
     */
    private static final Interceptor sLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            if (request.body() != null  ) {
                request.body().writeTo(requestBuffer);
            } else {
                Log.d("LogTAG", "request.body() == null");
            }
            //打印url信息
            LogUtil.i(" 》》》》  "," request.url() == "+request.url()+"");
            LogUtil.i(" 》》》》  "," request.body() == "+request.body()+"");
            Log.i(" url=  ",request.url() + (request.body() != null ? "?" + _parseParams(request.body(), requestBuffer) : ""));
            final Response response = chain.proceed(request);
            return response;
        }
    };

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable(BaseApplication.getInstance())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Log.e("》》》 ","no network");
            }
            Response originalResponse = chain.proceed(request);

            if (NetUtil.isNetworkAvailable(BaseApplication.getInstance())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
    public static int page = 1;
    private static final Interceptor tokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            LogUtil.i("response.code=" + response.code());
            LogUtil.i("  token ====  "+response.header("token"));

            if(page == 1){
                page++;
                FindItem findItem =  msgApi.getFindItem(2).execute().body();
                LogUtil.i("response  page = "+page);
                Request newRequest =  request.newBuilder().build();
                response.close();
                return chain.proceed(newRequest);
            }

//            HomeMiddleItem middleItem =  commonApi.getmiddleItem().execute().body();
//            LogUtil.i("response.code=  middleItem  " +middleItem);
            return response;
        }
    };

    private static void getData(){
        RetrofitService.getMiddleItem().doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).doOnNext(new Action1<HomeMiddleItem>() {
            @Override
            public void call(HomeMiddleItem middleItem) {

            }
        }).
                subscribe(new Subscriber<HomeMiddleItem>() {
                    @Override
                    public void onCompleted() {
                        Log.i("》》》》  "," response item middle === complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("》》》》  "," response middle onerror == "+e.toString());
                    }

                    @Override
                    public void onNext(HomeMiddleItem homeMiddleItem) {

//                Log.i("》》》》   "," middle thread  "+Thread.currentThread());
                        Log.i("》》》》  ","  response  item middle ===  onnext"+homeMiddleItem.toString());
                    }
                });
    }



    public static Observable<HomeMiddleItem> getMiddleItem(){
        return commonApi.getMiddle().subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<NovelItem> getNovel(int page){
        return msgApi.getNovel(page).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    public static Observable<FindItem> getFind(int page){
//        return msgApi.getFind(page).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
////                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread());
//    }



    @NonNull
    private static String _parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }

}
