package com.kaoyan.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaoyan.R;
import com.kaoyan.widget.EmptyLayout;
import com.kaoyan.widget.LoadingDialog;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tx on 2017/7/17.
 * fragment基类
 */
public abstract class BaseFragment extends RxFragment implements IBaseView {

    @Nullable
    @BindView(R.id.llEmpty)
    EmptyLayout emptyLayout;

    /**
     * 绑定布局文件
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * 业务操作，请求网络等
     */
    protected abstract void init();

    protected BaseActivity mActivity;

    //缓存Fragment view
    private View mRootView;
    protected boolean mIsMulti = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            ButterKnife.bind(this, mRootView);

        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            init();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            init();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    @Override
    public void showLoading() {
        if(emptyLayout != null){
            emptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if(emptyLayout != null){
            emptyLayout.hide();
        }
//        LoadingDialog.dismissDialog();
    }

    @Override
    public void showNetError() {

    }

    @Override
    public void loadNoData() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public void finishRefresh() {

    }
}
