package com.kaoyan.module.home;


import com.kaoyan.api.RetrofitService;
import com.kaoyan.model.BannerItem;
import com.kaoyan.utils.LogUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tx on 2017/7/25.
 * 首页presenter
 */

public class HomePresenter implements IHomePresent {

    private HomeView homeView;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void getData(boolean isRefresh) {

        RetrofitService.commonApi.getBanner().subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(homeView.<BannerItem>bindToLife())
                .subscribe(new Subscriber<BannerItem>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        homeView.hideLoading();
                    }

                    @Override
                    public void onNext(BannerItem bannerItem) {
                        LogUtil.i("  bannerItem  "+Thread.currentThread().getName());
                        homeView.showBanner(bannerItem);
                        homeView.hideLoading();
                    }
                });

    }

    @Override
    public void getMoreData() {

    }
}
