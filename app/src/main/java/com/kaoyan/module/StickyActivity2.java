package com.kaoyan.module;

import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.kaoyan.R;
import com.kaoyan.base.BaseActivity;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.widget.ObservableScrollView;

import butterknife.BindView;

/**
 * Created by tx on 2017/8/29.
 */

public class StickyActivity2 extends BaseActivity implements ObservableScrollView.Callbacks{

    @BindView(R.id.sticky)
    TextView sticky;

    @BindView(R.id.scrollView)
    ObservableScrollView scrollView;

    @BindView(R.id.placeHolder)
    TextView placeHolder;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_sticky2;
    }

    @Override
    protected void init() {
        sticky.setText("666666");
        scrollView.setCallbacks(this);
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                LogUtil.i("onGlobalLayout ==  ");
                onScrollChanged(scrollView.getScrollY());
            }
        });
    }

    @Override
    public void onScrollChanged(int t) {
        LogUtil.i("getScrollY ==  "+t);
        LogUtil.i("rgHolder.getTop() ==  "+placeHolder.getTop());
        int translation = Math.max(t,placeHolder.getTop());
        LogUtil.i("translation ==  "+translation);
        sticky.setTranslationY(translation);
    }

    @Override
    public void onTouchUp() {

    }

    @Override
    public void onTouchDown() {

    }
}
