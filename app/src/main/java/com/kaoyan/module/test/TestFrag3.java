package com.kaoyan.module.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaoyan.R;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.widget.SimpleViewPagerIndicator;

import butterknife.BindView;

/**
 * Created by tx on 2017/8/23.
 */

public class TestFrag3 extends BaseFragment {

    @BindView(R.id.id_stickynavlayout_indicator)
    SimpleViewPagerIndicator indicator;
    String[] title = {"1","2","3"};
    @Override
    protected int attachLayoutRes() {
        return R.layout.frag_test3;
    }

    @Override
    protected void init() {
        indicator.setTitles(title);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i(" test oncreateview 3333");
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.i(" test onActivityCreated 333333  "+getUserVisibleHint() + "  mIsMulti  "+mIsMulti);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        LogUtil.i(" test setUserVisibleHint 3333  "+isVisibleToUser + "  isVisible =" + isVisible() + "  mIsMulti  "+mIsMulti);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(" ondestroy  33333 ");
    }


}
