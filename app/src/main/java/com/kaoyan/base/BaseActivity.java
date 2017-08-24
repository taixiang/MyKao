package com.kaoyan.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaoyan.R;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.widget.EmptyLayout;
import com.kaoyan.widget.LoadingDialog;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.KeyEvent.KEYCODE_NOTIFICATION;

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
        if(attachLayoutRes() == 0){
            finish();
            return;
        }
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
        return this.<T>bindUntilEvent(ActivityEvent.DESTROY);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_HOME:return true;
//            case KeyEvent.KEYCODE_BACK:return true;
            case KeyEvent.KEYCODE_CALL:return true;
            case KeyEvent.KEYCODE_SYM: return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN: return true;
            case KeyEvent.KEYCODE_VOLUME_UP: return true;
            case KeyEvent.KEYCODE_STAR: return true;
            case KeyEvent.KEYCODE_SETTINGS:return true;
            case KeyEvent.KEYCODE_MENU:return true;
            case KeyEvent.KEYCODE_NOTIFICATION:return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //隐藏键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


}
