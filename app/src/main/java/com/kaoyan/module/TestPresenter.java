package com.kaoyan.module;

import com.kaoyan.view.IMainView;

/**
 * Created by tx on 2017/7/24.
 */

public class TestPresenter {
    private TestModule module;
    private IMainView mView;

    public TestPresenter(IMainView mView) {
        this.mView = mView;
        module = new TestModule2(mView);
    }

    public void getData(boolean isRefresh){
        module.getData(false);
    }
}
