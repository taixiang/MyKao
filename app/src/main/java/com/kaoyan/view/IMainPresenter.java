package com.kaoyan.view;

import android.os.Handler;
import android.util.Log;

import com.kaoyan.api.IApi;
import com.kaoyan.api.RetrofitService;
import com.kaoyan.base.IBasePresenter;
import com.kaoyan.model.BannerItem;
import com.kaoyan.model.BaseItem;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.model.LoginBean;
import com.kaoyan.model.LoginItem;
import com.kaoyan.module.LoginPresenter;
import com.kaoyan.utils.LogUtil;

import java.util.List;
import java.util.Timer;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tx on 2017/7/14.
 */
public class IMainPresenter implements LoginPresenter {
    private final IMainView mView;
    private int page = 1;

    public IMainPresenter(IMainView mView) {
        this.mView = mView;
    }

    @Override
    public void getData(final boolean isRefresh) {



//        RetrofitService.getMiddleItem().doOnSubscribe(new Action0() {
//            @Override
//            public void call() {
//
//            }
//        }).doOnNext(new Action1<HomeMiddleItem>() {
//            @Override
//            public void call(HomeMiddleItem middleItem) {
//
//            }
//        }).compose(mView.<HomeMiddleItem>bindToLife()).
//                subscribe(new Subscriber<HomeMiddleItem>() {
//            @Override
//            public void onCompleted() {
//                Log.i("》》》》  "," item middle === complete");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i("》》》》  ","middle onerror == "+e.toString());
//            }
//
//            @Override
//            public void onNext(HomeMiddleItem homeMiddleItem) {
//                mView.loadData(homeMiddleItem);
////                Log.i("》》》》   "," middle thread  "+Thread.currentThread());
//                Log.i("》》》》  "," item middle ===  onnext"+homeMiddleItem.toString());
//            }
//        });

        if(isRefresh){
            page = 1;
        }
        LogUtil.i(" present  getData ");
        mView.showLoading();
        doGetFind();
//        getFind(page).doOnSubscribe(new Action0() {
//            @Override
//            public void call() {
//                if(!isRefresh){
//                    mView.showLoading();
//                }
//            }
//        }).doOnNext(new Action1<FindItem>() {
//            @Override
//            public void call(FindItem middleItem) {
//
//            }
//        }).compose(mView.<FindItem>bindToLife()).
//                subscribe(new Subscriber<FindItem>() {
//                    @Override
//                    public void onCompleted() {
//                        mView.hideLoading();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.showNetError();
//                    }
//
//                    @Override
//                    public void onNext(final FindItem homeMiddleItem) {
//                        Log.i("》》》》》   "," finditem "+homeMiddleItem.toString());
//                        mView.loadNovel(homeMiddleItem);
//                        page++;
//                    }
//                });
    }
//    private Observable<FindItem> getFind(int page){
//        return RetrofitService.msgApi.getFind(page).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
////                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    @Override
    public void getMoreData() {
//        getFind(page).compose(mView.<FindItem>bindToLife()).
//                subscribe(new Subscriber<FindItem>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i("》》》》  ","novel more item === complete");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("》》》》  ","find more item === error ");
//                        mView.showNetError();
//                    }
//
//                    @Override
//                    public void onNext(FindItem homeMiddleItem) {
//                        Log.i("》》》》  ","find more item === next  ");
//                        mView.loadNovel(homeMiddleItem);
//                        page++;
//                    }
//                });
    }

    private void doSub(Observable ob){

        LoginBean bean = new LoginBean("test","111111","4544eff735d7303c4fbc906e7502b8c6086e16e8");
    }

    @Override
    public void login() {
        RetrofitService.toSub(RetrofitService.commonApi.login("test", "111111", "4544eff735d7303c4fbc906e7502b8c6086e16e8"), new Subscriber<LoginItem>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LoginItem loginItemBaseItem) {

            }
        }, mView.<LoginItem>bindToLife());
    }

    @Override
    public void loadBanner() {
        RetrofitService.commonApi.getBanner().subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.<BannerItem>bindToLife())
                .subscribe(new Subscriber<BannerItem>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(BannerItem bannerItem) {
                        LogUtil.i("  bannerItem  "+Thread.currentThread().getName());
                        mView.loadBanner(bannerItem);
                        mView.hideLoading();
                    }
                });
    }

    private void getData(){
//        getFind(page).flatMap(new Func1<FindItem, Observable<FindItem.Find>>() {
//            @Override
//            public Observable<FindItem.Find> call(FindItem findItem) {
//                return Observable.from(findItem.pros);
//            }
//        }).compose(mView.<FindItem.Find>bindToLife()).subscribe(new Subscriber<FindItem.Find>() {
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
//            public void onNext(FindItem.Find find) {
//                LogUtil.i(" flatmap  find === 》》》》  "+find.pro_name);
//            }
//        });
    }



    private void doGetFind(){

        RetrofitService.toSub(RetrofitService.msgApi.getFind(page), new Subscriber<List<FindItem.Find>>() {
            @Override
            public void onCompleted() {

                LogUtil.i("  onCompleted   ");

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i("  error   "+e.getMessage());
                mView.hideLoading();
            }

            @Override
            public void onNext(List<FindItem.Find> findItem) {
                LogUtil.i("  threadname onnext == "+Thread.currentThread().getName());

                mView.hideLoading();
                mView.loadFindList(findItem);
            }
        },mView.<List<FindItem.Find>>bindToLife());
    }
}
