package com.kaoyan.module.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaoyan.R;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.utils.LogUtil;

/**
 * Created by tx on 2017/8/23.
 */

public class TestFrag4 extends BaseFragment {
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_act;
    }

    @Override
    protected void init() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i(" test oncreateview 4444");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.i(" test onActivityCreated 44444  "+getUserVisibleHint() + "  mIsMulti  "+mIsMulti);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        LogUtil.i(" test setUserVisibleHint 44444  "+isVisibleToUser + "  isVisible =" + isVisible() + "  mIsMulti  "+mIsMulti);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(" ondestroy 44444 ");
    }


}
