package com.kaoyan.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaoyan.R;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.widget.EmptyLayout;
import com.kaoyan.widget.LoadingDialog;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tx on 2017/7/17.
 * activity基类
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView{
    protected BaseActivity mActivity;
    LoadingDialog dialog ;

    @Nullable
    @BindView(R.id.llEmpty)
    EmptyLayout emptyLayout;

    @Nullable
    @BindView(R.id.iv_back)
    ImageView iv_back;

    @Nullable
    @BindView(R.id.tv_title)
    TextView tv_title;

    @Nullable
    @BindView(R.id.right_container)
    LinearLayout right_ll;

    @Nullable
    @BindView(R.id.tv_right)
    TextView tv_right;

    @Nullable
    @BindView(R.id.iv_right)
    ImageView iv_right;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        mActivity = this;
        init();
    }

    /**
     * 标题栏左侧返回
     */
    public void goBack(){
        if(null != iv_back){
            iv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.finish();
                }
            });
        }
    }

    /**
     * 标题栏标题
     * @param title
     */
    public void setTitle(String title){
        if(null != tv_title){
            tv_title.setText(title);
        }
    }

    /**
     * 标题栏右侧文字
     * @param txt
     */
    public void setRightTxt(String txt){
        if(null != tv_right){
            tv_right.setText(txt);
        }
    }

    /**
     * 标题栏右侧图标
     */
    public void setRightImg(int imgId){
        if(null != iv_right){
            iv_right.setVisibility(View.VISIBLE);
            iv_right.setImageResource(imgId);
        }
    }

    @Override
    public void showLoading() {
//        if(emptyLayout != null){
//            emptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
//        }
        if(dialog == null){
            dialog = new LoadingDialog(mActivity);
        }
//        LoadingDialog.showDialog(this);
    }

    @Override
    public void hideLoading() {
//        if(emptyLayout != null){
//            emptyLayout.hide();
//        }
        if(dialog != null){
            dialog.dismissDialog();
        }
//        LoadingDialog.dismissDialog();
    }

    @Override
    public void showNetError() {
        if (emptyLayout != null) {
            emptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
        }
        LogUtil.i(" shownetError  baseactivity ");
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void loadNoData() {

    }


    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // 设置tag，不然下面 findFragmentByTag(tag)找不到
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            // 设置tag
            fragmentTransaction.replace(containerViewId, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 这里要设置tag，上面也要设置tag
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            getSupportFragmentManager().popBackStack(tag, 0);
        }
    }
}
