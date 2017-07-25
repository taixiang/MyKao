package com.kaoyan.module.test;

import com.kaoyan.api.RetrofitService;
import com.kaoyan.base.IBasePresenter;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.model.NovelItem;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.view.IMainView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tx on 2017/7/25.
 */

public class TestPresenter3 implements IBasePresenter {

    private IMainView view;

    public TestPresenter3(IMainView view) {
        this.view = view;
    }

    @Override
    public void getData(boolean isRefresh) {
        Observable<FindItem> t = RetrofitService.msgApi.getFind(1);
        Observable<HomeMiddleItem> l = RetrofitService.commonApi.getMiddle();
        Observable.merge(t,l).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).compose(view.<Object>bindToLife())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        if(o instanceof FindItem){
                            LogUtil.i(" merge FindItem "+((FindItem)o).toString());
                        }
                        if(o instanceof HomeMiddleItem){
                            LogUtil.i(" merge HomeMiddleItem "+((HomeMiddleItem)o).toString());
                        }
                    }
                });
    }



    @Override
    public void getMoreData() {

    }
}
