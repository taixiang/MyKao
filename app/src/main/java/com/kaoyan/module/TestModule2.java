package com.kaoyan.module;

import android.util.Log;

import com.kaoyan.api.RetrofitService;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.view.IMainView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by tx on 2017/7/24.
 */

public class TestModule2 implements TestModule {

    private IMainView mView;
    public TestModule2(IMainView mView) {
        this.mView = mView;
    }

    @Override
    public void getData(boolean isRefresh) {
        getFind().doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).doOnNext(new Action1<HomeMiddleItem>() {
            @Override
            public void call(HomeMiddleItem middleItem) {

            }
        }).compose(mView.<HomeMiddleItem>bindToLife()).
                subscribe(new Subscriber<HomeMiddleItem>() {
                    @Override
                    public void onCompleted() {
                        Log.i("》》》》  "," item middle === complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("》》》》  ","middle onerror == "+e.toString());
                    }

                    @Override
                    public void onNext(HomeMiddleItem homeMiddleItem) {
                        mView.loadData(homeMiddleItem);
//                Log.i("》》》》   "," middle thread  "+Thread.currentThread());
                        Log.i("》》》》  "," item middle ===  onnext"+homeMiddleItem.toString());
                    }
                });
    }

    private Observable<HomeMiddleItem> getFind(){
        return RetrofitService.commonApi.getMiddle().subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void getMoreData() {

    }
}
